package com.example.demo.service;

import com.example.demo.entity.BreachRule;

public interface BreachRuleService {

    BreachRule createRule(BreachRule rule);

    BreachRule updateRule(Long id, BreachRule rule);

    BreachRule getRuleById(Long id);

    BreachRule getActiveDefaultOrFirst();

    void deactivateRule(Long id);
}
