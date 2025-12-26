package com.example.demo.service.impl;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.PenaltyCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final PenaltyCalculationRepository penaltyCalculationRepository;
    private final ContractRepository contractRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return penaltyCalculationRepository.findAll();
    }

    @Override
    public PenaltyCalculation getCalculationById(Long calculationId) {
        return penaltyCalculationRepository.findById(calculationId).orElse(null);
    }
}
