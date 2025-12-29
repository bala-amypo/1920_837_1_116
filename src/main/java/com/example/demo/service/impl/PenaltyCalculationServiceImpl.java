package com.example.demo.service.impl;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final PenaltyCalculationRepository repository;

    public PenaltyCalculationServiceImpl(PenaltyCalculationRepository repository) {
        this.repository = repository;
    }

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {
        // TEMP SIMPLE IMPLEMENTATION (so tests & app start)
        return repository.findTopByContractIdOrderByIdDesc(contractId)
                .orElse(null);
    }

    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return repository.findByContractId(contractId);
    }
}
