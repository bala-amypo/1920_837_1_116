package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.exception.*;
import com.example.demo.service.PenaltyCalculationService;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final PenaltyCalculationRepository calcRepo;
    private final ContractRepository contractRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final BreachRuleRepository ruleRepo;

    public PenaltyCalculationServiceImpl(
            PenaltyCalculationRepository calcRepo,
            ContractRepository contractRepo,
            DeliveryRecordRepository deliveryRepo,
            BreachRuleRepository ruleRepo) {

        this.calcRepo = calcRepo;
        this.contractRepo = contractRepo;
        this.deliveryRepo = deliveryRepo;
        this.ruleRepo = ruleRepo;
    }

    public PenaltyCalculation calculatePenalty(Long contractId) {

        Contract contract = contractRepo.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        DeliveryRecord delivery = deliveryRepo
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new BadRequestException("No delivery record"));

        BreachRule rule = ruleRepo
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        long days = ChronoUnit.DAYS.between(
                contract.getAgreedDeliveryDate(),
                delivery.getDeliveryDate());

        int delay = (int) Math.max(days, 0);

        BigDecimal penalty = rule.getPenaltyPerDay()
                .multiply(BigDecimal.valueOf(delay));

        BigDecimal maxPenalty = contract.getBaseContractValue()
                .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage() / 100));

        penalty = penalty.min(maxPenalty);

        PenaltyCalculation calc = new PenaltyCalculation();
        calc.setContract(contract);
        calc.setBreachRule(rule);
        calc.setDaysDelayed(delay);
        calc.setCalculatedPenalty(penalty);

        return calcRepo.save(calc);
    }
}
