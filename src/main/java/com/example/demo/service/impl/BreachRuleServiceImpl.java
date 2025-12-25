package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
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
        if (rule.getPenaltyPerDay() == null || rule.getPenaltyPerDay().signum() <= 0) {
            throw new IllegalArgumentException("Penalty must be greater than zero");
        }
        return breachRuleRepository.save(rule);
    }

    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }
    @Override
public void deactivateRule(Long id) {
    BreachRule rule = breachRuleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rule not found"));
    rule.setActive(false);
    breachRuleRepository.save(rule);
}

}
