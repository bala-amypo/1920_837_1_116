package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.BreachReportService;

import java.util.List;
import org.springframework.stereotype.Service;

@Service

public class BreachReportServiceImpl implements BreachReportService {

    private BreachReportRepository breachReportRepository;
    private PenaltyCalculationRepository penaltyCalculationRepository;
    private ContractRepository contractRepository;

    public BreachReportServiceImpl() {}

    public BreachReportServiceImpl(
            BreachReportRepository breachReportRepository,
            PenaltyCalculationRepository penaltyCalculationRepository,
            ContractRepository contractRepository) {

        this.breachReportRepository = breachReportRepository;
        this.penaltyCalculationRepository = penaltyCalculationRepository;
        this.contractRepository = contractRepository;
    }

    @Override
    public BreachReport generateReport(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        PenaltyCalculation calculation = penaltyCalculationRepository
                .findTopByContractIdOrderByCalculatedAtDesc(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("No penalty calculation"));

        BreachReport report = BreachReport.builder()
                .contract(contract)
                .daysDelayed(calculation.getDaysDelayed())
                .penaltyAmount(calculation.getCalculatedPenalty())
                .build();

        return breachReportRepository.save(report);
    }

    @Override
    public BreachReport getReportById(Long id) {
        return breachReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found"));
    }

    @Override
    public List<BreachReport> getReportsForContract(Long contractId) {
        return breachReportRepository.findByContractId(contractId);
    }

    @Override
    public List<BreachReport> getAllReports() {
        return breachReportRepository.findAll();
    }
}
