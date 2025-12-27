package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private PenaltyCalculationRepository penaltyRepo;
    private ContractRepository contractRepo;
    private DeliveryRecordRepository deliveryRepo;
    private BreachRuleRepository ruleRepo;

    public PenaltyCalculationServiceImpl() {}

    public PenaltyCalculationServiceImpl(
            PenaltyCalculationRepository p,
            ContractRepository c,
            DeliveryRecordRepository d,
            BreachRuleRepository b) {
        this.penaltyRepo = p;
        this.contractRepo = c;
        this.deliveryRepo = d;
        this.ruleRepo = b;
    }

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {

        Contract contract = contractRepo.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        DeliveryRecord record = deliveryRepo
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery record not found"));

        BreachRule rule = ruleRepo
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        long diff = ChronoUnit.DAYS.between(
                contract.getAgreedDeliveryDate(),
                record.getDeliveryDate());

        int daysDelayed = diff > 0 ? (int) diff : 0;

        BigDecimal penalty =
        rule.getPenaltyPerDay().multiply(BigDecimal.valueOf(days));

        double maxPenalty =
                (contract.getBaseContractValue() * rule.getMaxPenaltyPercentage()) / 100.0;

        if (penalty > maxPenalty) penalty = maxPenalty;

        PenaltyCalculation pc = new PenaltyCalculation();
        pc.setDaysDelayed(daysDelayed);
        pc.setCalculatedPenalty(penalty);
        pc.setDaysDelayed(days);

        pc.setContract(contract);
        pc.setDeliveryRecord(record);
        pc.setBreachRule(rule);

        return penaltyRepo.save(pc);
    }

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return penaltyRepo.findByContractId(contractId);
    }

    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calculation not found"));
    }
}
