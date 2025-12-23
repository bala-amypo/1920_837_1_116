package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.entity.Contract;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.repository.ContractRepository;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository reportRepository;
    private final PenaltyCalculationRepository calculationRepository;
    private final ContractRepository contractRepository;

    public BreachReportServiceImpl(
            BreachReportRepository reportRepository,
            PenaltyCalculationRepository calculationRepository,
            ContractRepository contractRepository) {

        this.reportRepository = reportRepository;
        this.calculationRepository = calculationRepository;
        this.contractRepository = contractRepository;
    }

    @Override
    public BreachReport generateReport(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        PenaltyCalculation latestCalculation =
                calculationRepository
                        .findTopByContractIdOrderByCalculatedAtDesc(contractId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("No penalty calculation found"));

        BreachReport report = new BreachReport();
        report.setContract(contract);
        report.setDaysDelayed(latestCalculation.getDaysDelayed());
        report.setPenaltyAmount(latestCalculation.getCalculatedPenalty());
        report.setReportStatus("GENERATED");

        return reportRepository.save(report);
    }

    @Override
    public BreachReport getReportById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found"));
    }

    @Override
    public List<BreachReport> getReportsForContract(Long contractId) {
        return reportRepository.findByContractId(contractId);
    }

    @Override
    public List<BreachReport> getAllReports() {
        return reportRepository.findAll();
    }
}
