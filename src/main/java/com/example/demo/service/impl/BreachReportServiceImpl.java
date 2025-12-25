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

    public BreachReportServiceImpl() {}

    @Override
    public BreachReport getReportById(Long id) {
        return null;
    }

    @Override
    public BreachReport generateReport(Long contractId) {
        return null;
    }

    @Override
    public List<BreachReport> getReportsForContract(Long contractId) {
        return List.of();
    }
}
