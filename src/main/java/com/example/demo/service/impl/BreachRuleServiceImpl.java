package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // 🔴 THIS WAS MISSING
public class BreachRuleServiceImpl implements BreachRuleService {

    private final BreachRuleRepository repository;

    public BreachRuleServiceImpl(BreachRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public BreachRule createRule(BreachRule rule) {
        return repository.save(rule);
    }

    @Override
    public BreachRule updateRule(Long id, BreachRule rule) {
        rule.setId(id);
        return repository.save(rule);
    }

    @Override
    public void deactivateRule(Long id) {
        repository.findById(id).ifPresent(rule -> {
            rule.setActive(false);
            repository.save(rule);
        });
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return repository.findAll().stream().findFirst().orElse(null);
    }

    @Override
    public List<BreachRule> getAllRules() {
        return repository.findAll();
    }
}
