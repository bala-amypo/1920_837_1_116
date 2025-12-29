package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.BreachReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository breachRepo;
    private final ContractRepository contractRepo;
    private final PenaltyCalculationRepository penaltyRepo;

    @Override
    public List<BreachReport> getReportsForContract(Long contractId) {
        Contract contract = contractRepo.findById(contractId).orElseThrow();
        return breachRepo.findByContract(contract);
    }
}
