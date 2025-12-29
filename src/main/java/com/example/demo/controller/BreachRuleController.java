package com.example.demo.controller;

import com.example.demo.entity.BreachRule;
import com.example.demo.service.BreachRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-rules")
public class BreachRuleController {

    private final BreachRuleService service;

    public BreachRuleController(BreachRuleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BreachRule> create(@RequestBody BreachRule rule) {
        return ResponseEntity.ok(service.createRule(rule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BreachRule> update(@PathVariable Long id,
                                             @RequestBody BreachRule rule) {
        return ResponseEntity.ok(service.updateRule(id, rule));
    }

    @GetMapping
    public ResponseEntity<List<BreachRule>> getAll() {
        return ResponseEntity.ok(service.getAllRules());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivateRule(id);
        return ResponseEntity.ok().build();
    }
}
