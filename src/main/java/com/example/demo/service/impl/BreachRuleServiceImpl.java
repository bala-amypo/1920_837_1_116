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
    private BreachRuleRepository repository;

    // REQUIRED for tests
    public BreachRuleServiceImpl() {
    }

    public BreachRuleServiceImpl(BreachRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public BreachRule createRule(BreachRule rule) {
        return repository.save(rule);
    }

    @Override
    public BreachRule updateRule(Long id, BreachRule rule) {
        BreachRule existing = repository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setRuleName(rule.getRuleName());
        existing.setPenaltyPerDay(rule.getPenaltyPerDay());
        existing.setMaxPenaltyPercentage(rule.getMaxPenaltyPercentage());
        existing.setActive(rule.getActive());

        return repository.save(existing);
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
    public BreachRule getActiveDefaultOrFirst() {
        return repository.findAll()
                .stream()
                .filter(r -> Boolean.TRUE.equals(r.getActive()))
                .findFirst()
                .orElse(repository.findAll().isEmpty() ? null : repository.findAll().get(0));
    }

    @Override
    public void deactivateRule(Long id) {
        BreachRule rule = repository.findById(id).orElse(null);
        if (rule != null) {
            rule.setActive(false);
            repository.save(rule);
        }
    }
}
