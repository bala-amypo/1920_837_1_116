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

    // ✅ REQUIRED by TestNG tests
    public BreachRuleServiceImpl() {
        this.breachRuleRepository = null;
    }

    // ✅ REQUIRED by Spring
    public BreachRuleServiceImpl(BreachRuleRepository breachRuleRepository) {
        this.breachRuleRepository = breachRuleRepository;
    }

    @Override
    public BreachRule createRule(BreachRule rule) {

        if (rule.getPenaltyPerDay() == null ||
                rule.getPenaltyPerDay().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("penalty must be positive");
        }

        if (rule.getMaxPenaltyPercentage() < 0 ||
                rule.getMaxPenaltyPercentage() > 100) {
            throw new BadRequestException("percentage out of bounds");
        }

        breachRuleRepository.findByRuleName(rule.getRuleName())
                .ifPresent(r -> {
                    throw new BadRequestException("rule already exists");
                });

        return breachRuleRepository.save(rule);
    }

    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }
}
