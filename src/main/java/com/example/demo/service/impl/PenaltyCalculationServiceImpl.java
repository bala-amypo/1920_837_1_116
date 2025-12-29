package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.PenaltyCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final PenaltyCalculationRepository repository;

    @Override
    public PenaltyCalculation calculatePenalty(
            Contract contract,
            DeliveryRecord record,
            BreachRule rule
    ) {

        long days = ChronoUnit.DAYS.between(
                contract.getAgreedDeliveryDate(),
                record.getDeliveryDate()
        );

        int delayDays = Math.max((int) days, 0);

        // ✅ FIXED BigDecimal calculation
        BigDecimal dailyPenalty = rule.getPenaltyPerDay();
        BigDecimal totalPenalty = dailyPenalty.multiply(BigDecimal.valueOf(delayDays));

        BigDecimal maxAllowed = contract.getBaseContractValue()
                .multiply(rule.getMaxPenaltyPercentage())
                .divide(BigDecimal.valueOf(100));

        if (totalPenalty.compareTo(maxAllowed) > 0) {
            totalPenalty = maxAllowed;
        }

        PenaltyCalculation calculation = PenaltyCalculation.builder()
                .contract(contract)
                .breachRule(rule)
                .delayDays(delayDays)   // ✅ CORRECT FIELD
                .penaltyAmount(totalPenalty)
                .build();

        return repository.save(calculation);
    }
}
