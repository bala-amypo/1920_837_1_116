package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository breachReportRepository;
    private final ContractRepository contractRepository;
    private final PenaltyCalculationRepository penaltyCalculationRepository;

    public BreachReportServiceImpl(
            BreachReportRepository breachReportRepository,
            ContractRepository contractRepository,
            PenaltyCalculationRepository penaltyCalculationRepository) {
        this.breachReportRepository = breachReportRepository;
        this.contractRepository = contractRepository;
        this.penaltyCalculationRepository = penaltyCalculationRepository;
    }

    @Override
    public BreachReport generateReport(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        List<PenaltyCalculation> list =
                penaltyCalculationRepository.findByContractId(contractId);

        if (list.isEmpty()) {
            throw new ResourceNotFoundException("Penalty calculation not found");
        }

        PenaltyCalculation latest = list.get(list.size() - 1);

        BreachReport report = BreachReport.builder()
                .contract(contract)
                .daysDelayed(latest.getDaysDelayed())
                .penaltyAmount(latest.getCalculatedPenalty())
                .build();

        return breachReportRepository.save(report);
    }

    @Override
    public List<BreachReport> getAllReports() {
        return breachReportRepository.findAll();
    }

    @Override
    public List<BreachReport> getReportsForContract(Long contractId) {
        return breachReportRepository.findByContractId(contractId);
    }

    @Override
    public BreachReport getReportById(Long id) {
        return breachReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found"));
    }
}
