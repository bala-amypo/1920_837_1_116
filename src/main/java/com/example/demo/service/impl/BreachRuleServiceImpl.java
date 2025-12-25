package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BreachRuleServiceImpl implements BreachRuleService {

    private final BreachRuleRepository breachRuleRepository;

    @Override
    public BreachRule createRule(BreachRule rule) {
        return breachRuleRepository.save(rule);
    }

    @Override
    public BreachRule updateRule(Long id, BreachRule rule) {
        rule.setId(id);
        return breachRuleRepository.save(rule);
    }

    @Override
    public BreachRule getRuleById(Long id) {
        return breachRuleRepository.findById(id).orElse(null);
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElse(null);
    }

    @Override
    public void deactivateRule(Long id) {
        breachRuleRepository.findById(id).ifPresent(rule -> {
            rule.setActive(false);
            breachRuleRepository.save(rule);
        });
    }
}
