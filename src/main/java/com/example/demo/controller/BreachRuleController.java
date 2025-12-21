package com.example.demo.controller;

import com.example.demo.dto.BreachRuleDto;
import com.example.demo.entity.BreachRule;
import com.example.demo.service.BreachRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/breach-rules")
public class BreachRuleController {

    private final BreachRuleService breachRuleService;

    public BreachRuleController(BreachRuleService breachRuleService) {
        this.breachRuleService = breachRuleService;
    }

    @PostMapping
    public ResponseEntity<BreachRule> create(@RequestBody BreachRuleDto dto) {
        return ResponseEntity.ok(breachRuleService.createRule(dto));
    }

    @GetMapping("/active")
    public ResponseEntity<BreachRule> getActiveRule() {
        return ResponseEntity.ok(breachRuleService.getActiveDefaultOrFirst());
    }
}
