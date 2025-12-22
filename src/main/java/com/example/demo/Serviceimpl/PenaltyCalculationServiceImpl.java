package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.BreachRuleService;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

public class PenaltyCalculationServiceImpl {

    private final ContractRepository contractRepository;
    private final DeliveryRecordRepository deliveryRepository;
    private final BreachRuleService breachRuleService;
    private final PenaltyCalculationRepository penaltyRepository;

    public PenaltyCalculationServiceImpl(
            ContractRepository contractRepository,
            DeliveryRecordRepository deliveryRepository,
            BreachRuleService breachRuleService,
            PenaltyCalculationRepository penaltyRepository) {

        this.contractRepository = contractRepository;
        this.deliveryRepository = deliveryRepository;
        this.breachRuleService = breachRuleService;
        this.penaltyRepository = penaltyRepository;
    }

    public PenaltyCalculation calculate(String contractNumber) {

        Contract contract = contractRepository
                .findByContractNumber(contractNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contract not found"));

        DeliveryRecord delivery = deliveryRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(contract.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("No delivery record"));

        BreachRule rule = breachRuleService.getActiveDefaultOrFirst();

        long days = ChronoUnit.DAYS.between(
                contract.getAgreedDeliveryDate().toInstant(),
                delivery.getDeliveryDate().toInstant());

        int delay = (int) Math.max(days, 0);

        BigDecimal penaltyByDays =
                rule.getPenaltyPerDay().multiply(BigDecimal.valueOf(delay));

        BigDecimal maxPenalty =
                contract.getBaseContractValue()
                        .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage() / 100));

        BigDecimal finalPenalty = penaltyByDays.min(maxPenalty);

        PenaltyCalculation calc = new PenaltyCalculation();
        calc.setContract(contract);
        calc.setDaysDelayed(delay);
        calc.setCalculatedPenalty(finalPenalty);
        calc.setAppliedRule(rule);

        return penaltyRepository.save(calc);
    }
}
