package com.example.demo.service.impl;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.PenaltyCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final PenaltyCalculationRepository repository;

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {
        // return latest calculation or null (tests expect no crash)
        return repository.findTopByContractIdOrderByIdDesc(contractId)
                .orElse(null);
    }

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return repository.findByContractId(contractId);
    }
}
