package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
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

    public BreachReportServiceImpl() {
        this.breachReportRepository = null;
        this.penaltyCalculationRepository = null;
        this.contractRepository = null;
    }

    public BreachReportServiceImpl(
            BreachReportRepository breachReportRepository,
            PenaltyCalculationRepository penaltyCalculationRepository,
            ContractRepository contractRepository) {
        this.breachReportRepository = breachReportRepository;
        this.penaltyCalculationRepository = penaltyCalculationRepository;
        this.contractRepository = contractRepository;
    }

    @Override
    public List<BreachReport> getAllReports() {
        return breachReportRepository.findAll();
    }

    @Override
    public List<BreachReport> getReportsForContract(Long contractId) {
        return breachReportRepository.findAll();
    }

    // 🔥 MISSING METHOD — NOW FIXED
    @Override
    public BreachReport getReportById(Long id) {
        return breachReportRepository.findById(id).orElse(null);
    }
}
