package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    private BreachRuleRepository repository;

    public BreachRuleServiceImpl() {}

    public BreachRuleServiceImpl(BreachRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public BreachRule createRule(BreachRule rule) {
        return repository.save(rule);
    }

    @Override
public BreachRule getRuleById(Long id) {
    return repository.findById(id).orElse(null);
}


    @Override
    public List<BreachRule> getAllRules() {
        return repository.findAll();
    }

    @Override
    public BreachRule updateRule(Long id, BreachRule rule) {
        BreachRule existing = repository.findById(id).orElse(null);
        if (existing == null) return null;
        existing.setPenaltyPerDay(rule.getPenaltyPerDay());
        existing.setMaxPenaltyPercentage(rule.getMaxPenaltyPercentage());
        return repository.save(existing);
    }

    @Override
    public void deactivateRule(Long id) {
        BreachRule rule = repository.findById(id).orElse(null);
        if (rule != null) {
            rule.setActive(false);
            repository.save(rule);
        }
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return repository.findFirstByActiveTrueOrderByIsDefaultRuleDesc();
    }
}
