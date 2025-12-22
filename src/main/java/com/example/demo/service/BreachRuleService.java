package com.example.demo.service;

import com.example.demo.dto.BreachRuleDto;
import com.example.demo.entity.BreachRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachRuleRepository;
import org.springframework.stereotype.Service;

@Service
public class BreachRuleService {

    private final BreachRuleRepository breachRuleRepository;

    public BreachRuleService(BreachRuleRepository breachRuleRepository) {
        this.breachRuleRepository = breachRuleRepository;
    }

    public BreachRule createRule(BreachRuleDto dto) {

        BreachRule rule = new BreachRule();
        rule.setRuleName(dto.getRuleName());
        rule.setPenaltyPerDay(dto.getPenaltyPerDay());
        rule.setMaxPenaltyPercentage(dto.getMaxPenaltyPercentage());
        rule.setActive(dto.getActive());
        rule.setIsDefaultRule(dto.getIsDefaultRule());

        return breachRuleRepository.save(rule);
    }

    public BreachRule getActiveDefaultOrFirst() {
        return breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() ->
                        new ResourceNotFoundException("No active breach rule"));
    }
}
