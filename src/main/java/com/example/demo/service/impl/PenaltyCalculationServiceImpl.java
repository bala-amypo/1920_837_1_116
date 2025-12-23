package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class PenaltyCalculationServiceImpl {

    private final ContractRepository contractRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final BreachRuleService breachRuleService;
    private final PenaltyCalculationRepository penaltyRepo;

    public PenaltyCalculationServiceImpl(
            ContractRepository contractRepo,
            DeliveryRecordRepository deliveryRepo,
            BreachRuleService breachRuleService,
            PenaltyCalculationRepository penaltyRepo) {

        this.contractRepo = contractRepo;
        this.deliveryRepo = deliveryRepo;
        this.breachRuleService = breachRuleService;
        this.penaltyRepo = penaltyRepo;
    }

    public PenaltyCalculation calculate(String contractNumber) {

        Contract contract = contractRepo.findByContractNumber(contractNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contract not found"));

        DeliveryRecord delivery = deliveryRepo
                .findFirstByContractIdOrderByDeliveryDateDesc(contract.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("No delivery record"));

        BreachRule rule = breachRuleService.getActiveDefaultOrFirst();

        long daysDelayed = ChronoUnit.DAYS.between(
                contract.getAgreedDeliveryDate().toInstant(),
                delivery.getDeliveryDate().toInstant());

        int delay = (int) Math.max(daysDelayed, 0);

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

        return penaltyRepo.save(calc);
    }
}
