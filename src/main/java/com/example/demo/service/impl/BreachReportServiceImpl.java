package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.repository.*;
import com.example.demo.service.BreachReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository breachReportRepository;
    private final PenaltyCalculationRepository penaltyCalculationRepository;
    private final ContractRepository contractRepository;

    @Override
    public List<BreachReport> getAllReports() {
        return breachReportRepository.findAll();
    }
}
