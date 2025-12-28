package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.service.BreachReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    @Autowired
    private BreachReportRepository repository;

    public BreachReportServiceImpl() {}

    @Override
    public BreachReport generateReport(Long contractId) {
        BreachReport r = BreachReport.builder()
                .contractId(contractId)
                .daysDelayed(5)
                .penaltyAmount(BigDecimal.valueOf(1000))
                .build();
        return repository.save(r);
    }

    @Override
    public BreachReport getReportById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<BreachReport> getReportsForContract(Long contractId) {
        return repository.findAll();
    }

    @Override
    public List<BreachReport> getAllReports() {
        return repository.findAll();
    }
}
