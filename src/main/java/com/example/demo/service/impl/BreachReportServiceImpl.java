package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.entity.Contract;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.BreachReportService;

import java.util.Comparator;
import java.util.List;

public class BreachReportServiceImpl implements BreachReportService {

    private BreachReportRepository breachReportRepository;
    private PenaltyCalculationRepository penaltyCalculationRepository;
    private ContractRepository contractRepository;

    // Required by TestNG
    public BreachReportServiceImpl() {
    }

    // Required by Spring
    public BreachReportServiceImpl(BreachReportRepository breachReportRepository,
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

        List<PenaltyCalculation> calculations =
                penaltyCalculationRepository.findByContractId(contractId);

        if (calculations == null || calculations.isEmpty()) {
            throw new ResourceNotFoundException("No penalty calculation");
        }

        // Get latest calculation safely
        PenaltyCalculation latestCalculation = calculations.stream()
                .max(Comparator.comparing(PenaltyCalculation::getId))
                .orElseThrow(() -> new ResourceNotFoundException("No penalty calculation"));

        BreachReport report = BreachReport.builder()
                .contract(contract)
                .daysDelayed(latestCalculation.getDaysDelayed())
                .penaltyAmount(
                        latestCalculation.getCalculatedPenalty().doubleValue()
                )
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
