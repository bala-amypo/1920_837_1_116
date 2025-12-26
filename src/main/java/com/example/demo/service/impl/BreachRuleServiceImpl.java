package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;

import java.math.BigDecimal;
import java.util.List;

public class BreachRuleServiceImpl implements BreachRuleService {

    private BreachRuleRepository breachRuleRepository;

    public BreachRuleServiceImpl() {}

    public BreachRuleServiceImpl(BreachRuleRepository breachRuleRepository) {
        this.breachRuleRepository = breachRuleRepository;
    }

    @Override
    public BreachRule createRule(BreachRule rule) {
        if (rule.getPenaltyPerDay() == null ||
            rule.getPenaltyPerDay().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Invalid penalty");
        }

        if (rule.getMaxPenaltyPercentage() < 0 || rule.getMaxPenaltyPercentage() > 100) {
            throw new BadRequestException("Invalid percentage");
        }

        breachRuleRepository.findByRuleName(rule.getRuleName())
                .ifPresent(r -> { throw new BadRequestException("Rule already exists"); });

        return breachRuleRepository.save(rule);
    }

    @Override
    public BreachRule updateRule(Long id, BreachRule updated) {
        BreachRule existing = breachRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        existing.setPenaltyPerDay(updated.getPenaltyPerDay());
        existing.setMaxPenaltyPercentage(updated.getMaxPenaltyPercentage());
        existing.setActive(updated.getActive());
        existing.setIsDefaultRule(updated.getIsDefaultRule());

        return breachRuleRepository.save(existing);
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No active breach rule"));
    }

    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }

    @Override
    public void deactivateRule(Long id) {
        BreachRule rule = breachRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        rule.setActive(false);
        breachRuleRepository.save(rule);
    }
}
