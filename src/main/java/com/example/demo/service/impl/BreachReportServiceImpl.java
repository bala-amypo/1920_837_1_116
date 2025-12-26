package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository breachReportRepository;
    private final PenaltyCalculationRepository penaltyCalculationRepository;
    private final ContractRepository contractRepository;

    public BreachReportServiceImpl(BreachReportRepository breachReportRepository,
                                   PenaltyCalculationRepository penaltyCalculationRepository,
                                   ContractRepository contractRepository) {
        this.breachReportRepository = breachReportRepository;
        this.penaltyCalculationRepository = penaltyCalculationRepository;
        this.contractRepository = contractRepository;
    }

    @Override
    public BreachReport generateReport(Long contractId) {

        PenaltyCalculation calculation = penaltyCalculationRepository
                .findTopByContractIdOrderByCalculatedAtDesc(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("calculation not found"));

        BreachReport report = new BreachReport();
        report.setContract(calculation.getContract());
        report.setDaysDelayed(calculation.getDaysDelayed());
        report.setPenaltyAmount(calculation.getCalculatedPenalty());

        return breachReportRepository.save(report);
    }

    @Override
    public BreachReport getReportById(Long id) {
        return breachReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("report not found"));
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
