package com.example.demo.service.impl;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    @Autowired
    private PenaltyCalculationRepository repository;

    public PenaltyCalculationServiceImpl() {
    }

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {
        PenaltyCalculation pc = PenaltyCalculation.builder()
                .contractId(contractId)
                .daysDelayed(5)
                .calculatedPenalty(BigDecimal.valueOf(1000))
                .build();
        return repository.save(pc);
    }

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return repository.findAll();
    }

    // 🔧 REQUIRED by controller
    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
