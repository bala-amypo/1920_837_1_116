package com.example.demo.service.impl;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final PenaltyCalculationRepository repo;

    public PenaltyCalculationServiceImpl(PenaltyCalculationRepository repo) {
        this.repo = repo;
    }

    // ✅ REMOVE WRONG @Override METHODS YOU ADDED EARLIER

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return repo.findByContractId(contractId);
    }

    // ✅ MISSING METHOD
    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calculation not found"));
    }

    @Override
public PenaltyCalculation calculatePenalty(Long contractId) {
    return repo.findByContractId(contractId)
            .stream()
            .reduce((first, second) -> second) // get latest
            .orElseThrow(() -> new ResourceNotFoundException("Penalty calculation not found"));
}

}
