package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private PenaltyCalculationRepository penaltyCalculationRepository;
    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;
    private BreachRuleRepository breachRuleRepository;

    // REQUIRED by tests
    public PenaltyCalculationServiceImpl() {
    }

    // OPTIONAL for Spring
    public PenaltyCalculationServiceImpl(
            PenaltyCalculationRepository penaltyCalculationRepository,
            ContractRepository contractRepository,
            DeliveryRecordRepository deliveryRecordRepository,
            BreachRuleRepository breachRuleRepository) {

        this.penaltyCalculationRepository = penaltyCalculationRepository;
        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.breachRuleRepository = breachRuleRepository;
    }

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        DeliveryRecord record = deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery record not found"));

        BreachRule rule = breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        long diff = ChronoUnit.DAYS.between(
                contract.getAgreedDeliveryDate(),
                record.getDeliveryDate()
        );

        int daysDelayed = diff > 0 ? (int) diff : 0;

        double penalty = daysDelayed * rule.getPenaltyPerDay();

        double maxPenalty =
                (contract.getBaseContractValue() * rule.getMaxPenaltyPercentage()) / 100.0;

        if (penalty > maxPenalty) {
            penalty = maxPenalty;
        }

        PenaltyCalculation pc = new PenaltyCalculation();
        pc.setContract(contract);
        pc.setDeliveryRecord(record);
        pc.setBreachRule(rule);
        pc.setDaysDelayed(daysDelayed);
        pc.setCalculatedPenalty(penalty);

        return penaltyCalculationRepository.save(pc);
    }

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return penaltyCalculationRepository.findByContractId(contractId);
    }
    @Override
public PenaltyCalculation getCalculationById(Long id) {
    return penaltyCalculationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Calculation not found"));
}

}
