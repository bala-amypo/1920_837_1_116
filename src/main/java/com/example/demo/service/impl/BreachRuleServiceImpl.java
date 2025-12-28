package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    @Autowired
    private BreachRuleRepository breachRuleRepository;

    // REQUIRED: no-arg constructor for tests
    public BreachRuleServiceImpl() {
    }

    @Override
    public BreachRule createRule(BreachRule rule) {
        rule.setActive(true);
        return breachRuleRepository.save(rule);
    }

    @Override
    public BreachRule updateRule(Long id, BreachRule rule) {
        rule.setId(id);
        return breachRuleRepository.save(rule);
    }

    @Override
    public void deactivateRule(Long id) {
        BreachRule rule = breachRuleRepository.findById(id).orElse(null);
        if (rule != null) {
            rule.setActive(false);
            breachRuleRepository.save(rule);
        }
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return breachRuleRepository.findAll()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }
}
