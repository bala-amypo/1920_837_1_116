package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private BreachReportRepository repository;

    public BreachReportServiceImpl() {
    }

    public BreachReportServiceImpl(BreachReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public BreachReport generateReport(Long contractId) {
        return BreachReport.builder()
                .contractId(contractId)
                .build();
    }

    @Override
    public BreachReport getReportById(Long id) {
        return repository != null ? repository.findById(id).orElse(null) : null;
    }

    @Override
    public List<BreachReport> getAllReports() {
        return repository != null ? repository.findAll() : List.of();
    }

    // ✅ REQUIRED BY CONTROLLER + TESTS
    @Override
    public List<BreachReport> getReportsForContract(Long contractId) {
        return repository != null
                ? repository.findByContractId(contractId)
                : List.of();
    }
}
