package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.BreachReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository repository;
    private final ContractRepository contractRepository;

    @Override
    public BreachReport generateReport(Long contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow();

        BreachReport report = BreachReport.builder()
                .contract(contract)
                .totalPenalty(BigDecimal.ZERO)
                .build();

        return repository.save(report);
    }

    @Override
    public BreachReport getReportById(Long id) {
        return repository.findById(id).orElseThrow();
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
