package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.entity.Contract;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository breachReportRepository;
    private final ContractRepository contractRepository;
    private final PenaltyCalculationRepository penaltyCalculationRepository;

    public BreachReportServiceImpl(BreachReportRepository breachReportRepository,
                                   ContractRepository contractRepository,
                                   PenaltyCalculationRepository penaltyCalculationRepository) {
        this.breachReportRepository = breachReportRepository;
        this.contractRepository = contractRepository;
        this.penaltyCalculationRepository = penaltyCalculationRepository;
    }

    @Override
    public BreachReport generateReport(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow();

        PenaltyCalculation latest = penaltyCalculationRepository
                .findTopByContractOrderByIdDesc(contract)
                .orElseThrow();

        BreachReport report = BreachReport.builder()
                .contract(contract)
                .daysDelayed(latest.getDaysDelayed())
                .totalPenalty(latest.getCalculatedPenalty())
                .build();

        return breachReportRepository.save(report);
    }

    @Override
    public List<BreachReport> getAllReports() {
        return breachReportRepository.findAll();
    }

    @Override
    public BreachReport getReportById(Long id) {
        return breachReportRepository.findById(id).orElseThrow();
    }

    @Override
    public List<BreachReport> getReportsForContract(Long contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow();
        return breachReportRepository.findByContract(contract);
    }
}
