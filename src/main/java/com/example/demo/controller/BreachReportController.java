package com.example.demo.controller;

import com.example.demo.entity.BreachReport;
import com.example.demo.service.BreachReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class BreachReportController {

    private final BreachReportService breachReportService;

    public BreachReportController(BreachReportService breachReportService) {
        this.breachReportService = breachReportService;
    }

    @GetMapping("/{contractId}")
    public ResponseEntity<List<BreachReport>> getReports(@PathVariable Long contractId) {
        return ResponseEntity.ok(
                breachReportService.getReportsByContract(contractId));
    }
}
