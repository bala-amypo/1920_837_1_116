package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void deactivateRule(Long id) {
        breachRuleRepository.findById(id).ifPresent(rule -> {
            rule.setActive(false);
            breachRuleRepository.save(rule);
        });
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return breachRuleRepository.findAll()
                .stream()
                .findFirst()
                .orElse(null);
    }

    // ✅ THIS WAS MISSING
    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }
}
