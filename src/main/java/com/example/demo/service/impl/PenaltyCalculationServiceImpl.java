package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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

        int days = deliveryRecord.getDaysDelayed();

        BigDecimal penalty =
                rule.getPenaltyPerDay().multiply(BigDecimal.valueOf(days));

        PenaltyCalculation pc = new PenaltyCalculation();
        pc.setContract(contract);
        pc.setDeliveryRecord(deliveryRecord);
        pc.setBreachRule(rule);
        pc.setDaysDelayed(days);
        pc.setCalculatedPenalty(penalty);

        return repo.save(pc);
    }

    // ✅ MISSING METHOD
    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return repo.findByContractId(contractId);
    }
}
