package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;

@Service
public class PenaltyCalculationService {

    private final ContractRepository contractRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;
    private final PenaltyCalculationRepository penaltyCalculationRepository;
    private final BreachRuleService breachRuleService;

    public PenaltyCalculationService(
            ContractRepository contractRepository,
            DeliveryRecordRepository deliveryRecordRepository,
            PenaltyCalculationRepository penaltyCalculationRepository,
            BreachRuleService breachRuleService) {

        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.penaltyCalculationRepository = penaltyCalculationRepository;
        this.breachRuleService = breachRuleService;
    }

    public PenaltyCalculation calculatePenalty(String contractNumber) {

        Contract contract = contractRepository
                .findByContractNumber(contractNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contract not found"));

        DeliveryRecord delivery = deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(contract.getId())
                .orElseThrow(() ->
                        new BadRequestException("No delivery record"));

        long delayDays = ChronoUnit.DAYS.between(
                contract.getAgreedDeliveryDate().toInstant(),
                delivery.getDeliveryDate().toInstant());

        int daysDelayed = (int) Math.max(delayDays, 0);

        BreachRule rule = breachRuleService.getActiveDefaultOrFirst();

        BigDecimal perDayPenalty =
                rule.getPenaltyPerDay().multiply(BigDecimal.valueOf(daysDelayed));

        BigDecimal maxPenalty =
                contract.getBaseContractValue()
                        .multiply(BigDecimal.valueOf(
                                rule.getMaxPenaltyPercentage() / 100));

        BigDecimal finalPenalty = perDayPenalty.min(maxPenalty);

        PenaltyCalculation calculation = new PenaltyCalculation();
        calculation.setContract(contract);
        calculation.setDaysDelayed(daysDelayed);
        calculation.setCalculatedPenalty(finalPenalty);
        calculation.setAppliedRule(rule);
        calculation.setCalculatedAt(new Timestamp(System.currentTimeMillis()));

        return penaltyCalculationRepository.save(calculation);
    }
}
