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

    // REQUIRED: No-arg constructor (tests use new BreachRuleServiceImpl())
    public BreachRuleServiceImpl() {
    }

    // Optional constructor (Spring use)
    public BreachRuleServiceImpl(BreachRuleRepository breachRuleRepository) {
        this.breachRuleRepository = breachRuleRepository;
    }

    @Override
    public BreachRule createRule(BreachRule rule) {
        return breachRuleRepository.save(rule);
    }

    @Override
    public BreachRule getRuleById(Long id) {
        return breachRuleRepository.findById(id).orElse(null);
    }

    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }
}
