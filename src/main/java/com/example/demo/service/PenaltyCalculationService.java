package com.example.demo.service;

import com.example.demo.entity.PenaltyCalculation;
import java.util.List;

public interface PenaltyCalculationService {

    PenaltyCalculation calculatePenalty(Long contractId);

    List<PenaltyCalculation> getCalculationsForContract(Long contractId);
}
