package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.service.BreachReportService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository repository;

    @Override
    public BreachReport generateReport(Long contractId) {
        BreachReport report = new BreachReport();
        report.setContractId(contractId);
        return repository.save(report);
    }

    @Override
    public BreachReport getReportById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));
    }

    @Override
    public List<BreachReport> getReportsForContract(Long contractId) {
        return repository.findByContractId(contractId);
    }

    @Override
    public List<BreachReport> getAllReports() {
        return repository.findAll();
    }
}
