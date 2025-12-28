package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    private final BreachRuleRepository repo;

    public BreachRuleServiceImpl(BreachRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public BreachRule createRule(BreachRule rule) {
        rule.setActive(true);
        return repo.save(rule);
    }

    @Override
    public void deactivateRule(Long id) {
        BreachRule rule = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
        rule.setActive(false);
        repo.save(rule);
    }

    // ✅ MISSING METHOD
    @Override
    public List<BreachRule> getAllRules() {
        return repo.findAll();
    }
    @Override
public BreachRule getActiveDefaultOrFirst() {
    return repo.findAll()
            .stream()
            .filter(BreachRule::getActive)
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException("No active breach rule found"));
}

}
