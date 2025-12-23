package com.example.demo.repository;

import com.example.demo.entity.BreachRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface BreachRuleRepository extends JpaRepository<BreachRule, Long> {

    Optional<BreachRule> findByRuleName(String ruleName);

    List<BreachRule> findAll();

    Optional<BreachRule> findFirstByActiveTrueOrderByIsDefaultRuleDesc();
}
