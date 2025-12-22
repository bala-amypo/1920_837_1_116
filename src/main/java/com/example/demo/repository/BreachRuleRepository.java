
package com.example.demo.repository;

public interface BreachRuleRepository extends JpaRepository<BreachRule, Long> {
    Optional<BreachRule> findFirstByActiveTrueOrderByIsDefaultRuleDesc();
}
