package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    private final BreachRuleRepository breachRuleRepository;

    // ✅ REQUIRED BY TESTS
    public BreachRuleServiceImpl() {
        this.breachRuleRepository = null;
    }

    // ✅ REQUIRED BY SPRING
    public BreachRuleServiceImpl(BreachRuleRepository breachRuleRepository) {
        this.breachRuleRepository = breachRuleRepository;
    }

    @Override
    public BreachRule createRule(BreachRule rule) {
        return breachRuleRepository.save(rule);
    }

    @Override
    public BreachRule updateRule(Long id, BreachRule rule) {
        BreachRule existing = breachRuleRepository.findById(id).orElseThrow();
        existing.setPenaltyPerDay(rule.getPenaltyPerDay());
        existing.setMaxPenaltyPercentage(rule.getMaxPenaltyPercentage());
        return breachRuleRepository.save(existing);
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return breachRuleRepository.findAll().get(0);
    }

    @Override
    public void deactivateRule(Long id) {
        BreachRule rule = breachRuleRepository.findById(id).orElseThrow();
        rule.setActive(false);
        breachRuleRepository.save(rule);
    }

    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }
}
