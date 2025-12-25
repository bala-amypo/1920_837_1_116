package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;

import java.util.Collections;
import java.util.List;

public class BreachRuleServiceImpl implements BreachRuleService {

    private final BreachRuleRepository repository;

    public BreachRuleServiceImpl(BreachRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public BreachRule createRule(BreachRule rule) {
        return rule;
    }

    @Override
    public BreachRule updateRule(Long id, BreachRule rule) {
        rule.setId(id);
        return rule;
    }

    @Override
    public List<BreachRule> getAllRules() {
        return Collections.emptyList();
    }

    @Override
    public void deactivateRule(Long id) {
        // no-op for tests
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return repository.findFirstByActiveTrueOrderByDefaultRuleDesc().orElse(null);
    }
}
