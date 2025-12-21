package com.example.demo.service;

import com.example.demo.entity.BreachReport;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BreachReportService {

    private final BreachReportRepository breachReportRepository;
    private final PenaltyCalculationRepository penaltyCalculationRepository;

    public BreachReportService(BreachReportRepository breachReportRepository,
                               PenaltyCalculationRepository penaltyCalculationRepository) {
        this.breachReportRepository = breachReportRepository;
        this.penaltyCalculationRepository = penaltyCalculationRepository;
    }

    public List<BreachReport> getReportsByContract(Long contractId) {

        PenaltyCalculation calculation =
                penaltyCalculationRepository
                        .findTopByContractIdOrderByCalculatedAtDesc(contractId)
                        .orElseThrow(() ->
                                new BadRequestException("No penalty calculation"));

        BreachReport report = new BreachReport();
        report.setContract(calculation.getContract());
        report.setDaysDelayed(calculation.getDaysDelayed());
        report.setPenaltyAmount(calculation.getCalculatedPenalty());
        report.setReportGeneratedAt(new Timestamp(System.currentTimeMillis()));
        report.setRemarks("Auto-generated breach report");

        breachReportRepository.save(report);

        return breachReportRepository.findByContractId(contractId);
    }
}
