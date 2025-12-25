package com.example.demo.service.impl;

import com.example.demo.repository.*;
import com.example.demo.service.BreachReportService;
import com.example.demo.entity.BreachReport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository breachReportRepository;
    private final PenaltyCalculationRepository penaltyCalculationRepository;
    private final ContractRepository contractRepository;

    // ✅ REQUIRED BY TESTS
    public BreachReportServiceImpl() {
        this.breachReportRepository = null;
        this.penaltyCalculationRepository = null;
        this.contractRepository = null;
    }

    // ✅ REQUIRED BY SPRING
    public BreachReportServiceImpl(BreachReportRepository breachReportRepository,
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
}
