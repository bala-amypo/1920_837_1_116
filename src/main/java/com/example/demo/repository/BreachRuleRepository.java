package com.example.demo.repository;

import com.example.demo.entity.BreachRule;
import java.util.Optional;

public interface BreachRuleRepository {
    Optional<BreachRule> findFirstByActiveTrueOrderByDefaultRuleDesc();
}
