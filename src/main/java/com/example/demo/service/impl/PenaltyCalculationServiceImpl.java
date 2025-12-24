package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final PenaltyCalculationRepository calculationRepo;
    private final ContractRepository contractRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final BreachRuleRepository ruleRepo;

    public PenaltyCalculationServiceImpl(
            PenaltyCalculationRepository calculationRepo,
            ContractRepository contractRepo,
            DeliveryRecordRepository deliveryRepo,
            BreachRuleRepository ruleRepo) {

        this.calculationRepo = calculationRepo;
        this.contractRepo = contractRepo;
        this.deliveryRepo = deliveryRepo;
        this.ruleRepo = ruleRepo;
    }

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {

        Contract contract = contractRepo.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        DeliveryRecord delivery = deliveryRepo
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("No delivery record"));

        BreachRule rule = ruleRepo
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No active breach rule"));

        LocalDate agreedDate = contract.getAgreedDeliveryDate();
        LocalDate actualDate = delivery.getDeliveryDate();

        long daysDelayed = ChronoUnit.DAYS.between(agreedDate, actualDate);
        if (daysDelayed < 0) {
            daysDelayed = 0;
        }

        BigDecimal rawPenalty =
                rule.getPenaltyPerDay().multiply(BigDecimal.valueOf(daysDelayed));

        BigDecimal maxPenalty =
                contract.getBaseContractValue()
                        .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage() / 100));

        BigDecimal finalPenalty =
                rawPenalty.compareTo(maxPenalty) > 0 ? maxPenalty : rawPenalty;

        PenaltyCalculation calc = new PenaltyCalculation();
        calc.setContract(contract);
        calc.setDaysDelayed((int) daysDelayed);
        calc.setCalculatedPenalty(finalPenalty);
        calc.setCalculatedAt(LocalDateTime.now());

        return calculationRepo.save(calc);
    }

    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return calculationRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calculation not found"));
    }

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return calculationRepo.findByContractId(contractId);
    }
}
