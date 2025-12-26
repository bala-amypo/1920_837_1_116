package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final PenaltyCalculationRepository penaltyCalculationRepository;
    private final ContractRepository contractRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;
    private final BreachRuleRepository breachRuleRepository;

    public PenaltyCalculationServiceImpl(PenaltyCalculationRepository penaltyCalculationRepository,
                                         ContractRepository contractRepository,
                                         DeliveryRecordRepository deliveryRecordRepository,
                                         BreachRuleRepository breachRuleRepository) {
        this.penaltyCalculationRepository = penaltyCalculationRepository;
        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.breachRuleRepository = breachRuleRepository;
    }

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("contract not found"));

        DeliveryRecord record = deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new BadRequestException("delivery record missing"));

        long daysDelayed = ChronoUnit.DAYS.between(
                contract.getAgreedDeliveryDate(),
                record.getDeliveryDate()
        );

        if (daysDelayed < 0) daysDelayed = 0;

        BreachRule rule = breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new BadRequestException("inactive rule"));

        BigDecimal penalty = rule.getPenaltyPerDay()
                .multiply(BigDecimal.valueOf(daysDelayed));

        BigDecimal maxAllowed = contract.getBaseContractValue()
                .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage() / 100));

        if (penalty.compareTo(maxAllowed) > 0) {
            penalty = maxAllowed;
        }

        PenaltyCalculation calculation = new PenaltyCalculation();
        calculation.setContract(contract);
        calculation.setDeliveryRecord(record);
        calculation.setBreachRule(rule);
        calculation.setDaysDelayed((int) daysDelayed);
        calculation.setCalculatedPenalty(penalty);

        return penaltyCalculationRepository.save(calculation);
    }

    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyCalculationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("calculation not found"));
    }

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return penaltyCalculationRepository.findByContractId(contractId);
    }
}
