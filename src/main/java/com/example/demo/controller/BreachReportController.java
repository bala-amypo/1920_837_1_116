package com.example.demo.controller;

import com.example.demo.entity.BreachReport;
import com.example.demo.service.BreachReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/breach-reports")
@RequiredArgsConstructor
public class BreachReportController {

    private final BreachReportService service;

    @PostMapping("/{contractId}")
    public BreachReport generate(@PathVariable Long contractId) {
        return service.generateReport(contractId);
    }

    @GetMapping("/{id}")
    public BreachReport getById(@PathVariable Long id) {
        return service.getReportById(id);
    }

    @GetMapping("/contract/{contractId}")
    public List<BreachReport> byContract(@PathVariable Long contractId) {
        return service.getReportsForContract(contractId);
    }

    @GetMapping
    public List<BreachReport> all() {
        return service.getAllReports();
    }
}
