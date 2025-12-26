package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    private final BreachRuleRepository breachRuleRepository;

    public BreachRuleServiceImpl(BreachRuleRepository breachRuleRepository) {
        this.breachRuleRepository = breachRuleRepository;
    }

    @Override
    public BreachRule createRule(BreachRule rule) {

        if (breachRuleRepository.findByRuleName(rule.getRuleName()).isPresent()) {
            throw new BadRequestException("planName exists");
        }

        if (rule.getPenaltyPerDay().signum() <= 0) {
            throw new BadRequestException("invalid penalty");
        }

        if (rule.getMaxPenaltyPercentage() < 0 || rule.getMaxPenaltyPercentage() > 100) {
            throw new BadRequestException("invalid percentage");
        }

        rule.setActive(true);
        return breachRuleRepository.save(rule);
    }

    @Override
    public BreachRule updateRule(Long id, BreachRule rule) {

        BreachRule existing = breachRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("rule not found"));

        existing.setPenaltyPerDay(rule.getPenaltyPerDay());
        existing.setMaxPenaltyPercentage(rule.getMaxPenaltyPercentage());
        existing.setIsDefaultRule(rule.getIsDefaultRule());

        return breachRuleRepository.save(existing);
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return breachRuleRepository.findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new BadRequestException("inactive rule"));
    }

    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }

    @Override
    public void deactivateRule(Long id) {
        BreachRule rule = breachRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("rule not found"));

        rule.setActive(false);
        breachRuleRepository.save(rule);
    }
}
