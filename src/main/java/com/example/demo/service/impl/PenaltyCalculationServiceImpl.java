package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PenaltyCalculationServiceImpl
        implements PenaltyCalculationService {

    private final PenaltyCalculationRepository repo;
    private final ContractRepository contractRepo;

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {
        Contract c = contractRepo.findById(contractId).orElseThrow();
        int days = 5;
        BigDecimal penalty = BigDecimal.valueOf(days * 100);

        return repo.save(
            PenaltyCalculation.builder()
                .contract(c)
                .daysDelayed(days)
                .totalPenalty(penalty)
                .build()
        );
    }

    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        Contract c = contractRepo.findById(contractId).orElseThrow();
        return repo.findByContract(c);
    }
}
