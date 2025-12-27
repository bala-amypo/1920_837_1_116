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

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository breachReportRepository;
    private final ContractRepository contractRepository;
    private final PenaltyCalculationRepository penaltyCalculationRepository;

    public BreachReportServiceImpl(
            BreachReportRepository b,
            ContractRepository c,
            PenaltyCalculationRepository p) {
        this.breachReportRepository = b;
        this.contractRepository = c;
        this.penaltyCalculationRepository = p;
    }

    @Override
    public BreachReport generateReport(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        PenaltyCalculation calc =
                penaltyCalculationRepository.findTopByContractIdOrderByIdDesc(contractId)
                        .orElseThrow(() -> new ResourceNotFoundException("Penalty calculation not found"));

        BreachReport report = new BreachReport();
        report.setContract(contract);
        report.setDaysDelayed(calc.getDaysDelayed());
        report.setPenaltyAmount(calc.getCalculatedPenalty()); // ✅ double → double

        return breachReportRepository.save(report);
    }
}
