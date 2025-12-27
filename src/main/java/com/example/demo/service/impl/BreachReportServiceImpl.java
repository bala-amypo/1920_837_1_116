package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.entity.Contract;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository breachReportRepository;
    private final ContractRepository contractRepository;
    private final PenaltyCalculationRepository penaltyCalculationRepository;

    public BreachReportServiceImpl(
            BreachReportRepository breachReportRepository,
            ContractRepository contractRepository,
            PenaltyCalculationRepository penaltyCalculationRepository) {

        this.breachReportRepository = breachReportRepository;
        this.contractRepository = contractRepository;
        this.penaltyCalculationRepository = penaltyCalculationRepository;
    }

    @Override
    public BreachReport generateReport(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        List<PenaltyCalculation> calculations =
                penaltyCalculationRepository.findByContractId(contractId);

        if (calculations.isEmpty()) {
            throw new ResourceNotFoundException("Penalty calculation not found");
        }

        PenaltyCalculation latest = calculations.get(calculations.size() - 1);

        BreachReport report = new BreachReport();
        report.setContract(contract);
        report.setDaysDelayed(latest.getDaysDelayed());
        report.setPenaltyAmount(latest.getCalculatedPenalty()); // ✅ double → double

        return breachReportRepository.save(report);
    }

    @Override
    public List<BreachReport> getAllReports() {
        return breachReportRepository.findAll();
    }
}
