package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    private final BreachRuleRepository repo;

    public BreachRuleServiceImpl(BreachRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public BreachRule createRule(BreachRule rule) {

        if (rule.getPenaltyPerDay().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Penalty per day must be positive");
        }

        if (rule.getMaxPenaltyPercentage().compareTo(BigDecimal.ZERO) < 0 ||
            rule.getMaxPenaltyPercentage().compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new BadRequestException("Max penalty percentage must be between 0 and 100");
        }

        rule.setActive(true);
        return repo.save(rule);
    }
}
