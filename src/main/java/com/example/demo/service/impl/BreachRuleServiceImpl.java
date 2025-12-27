package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    private BreachRuleRepository repo;

    public BreachRuleServiceImpl() {}

    public BreachRuleServiceImpl(BreachRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public BreachRule createRule(BreachRule rule) {
        if (rule.getPenaltyPerDay() <= 0) {
            throw new BadRequestException("Penalty must be greater than zero");
        }
        if (rule.getMaxPenaltyPercentage() < 1 || rule.getMaxPenaltyPercentage() > 100) {
            throw new BadRequestException("Max penalty percentage must be between 1 and 100");
        }
        return repo.save(rule);
    }

    @Override
    public BreachRule updateRule(Long id, BreachRule rule) {
        BreachRule existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
        existing.setPenaltyPerDay(rule.getPenaltyPerDay());
        existing.setMaxPenaltyPercentage(rule.getMaxPenaltyPercentage());
        return repo.save(existing);
    }

    @Override
    public void deactivateRule(Long id) {
        BreachRule rule = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
        rule.setActive(false);
        repo.save(rule);
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return repo.findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No active rule found"));
    }

    @Override
    public List<BreachRule> getAllRules() {
        return repo.findAll();
    }
}
