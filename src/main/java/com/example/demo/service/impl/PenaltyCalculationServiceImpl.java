package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final PenaltyCalculationRepository repo;

    public PenaltyCalculationServiceImpl(PenaltyCalculationRepository repo) {
        this.repo = repo;
    }

    @Override
    public PenaltyCalculation calculatePenalty(
            DeliveryRecord deliveryRecord,
            Contract contract,
            BreachRule rule) {

        int days = deliveryRecord.getDaysDelayed(); // ✅ FIX

        BigDecimal penalty =
                rule.getPenaltyPerDay()
                        .multiply(BigDecimal.valueOf(days)); // ✅ FIX

        PenaltyCalculation pc = new PenaltyCalculation();
        pc.setDeliveryRecord(deliveryRecord);
        pc.setContract(contract);
        pc.setBreachRule(rule);
        pc.setDaysDelayed(days); // ✅ FIX
        pc.setCalculatedPenalty(penalty); // ✅ FIX

        return repo.save(pc);
    }
}
