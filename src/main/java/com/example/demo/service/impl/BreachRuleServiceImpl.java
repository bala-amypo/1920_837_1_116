package com.example.demo.service.impl;

import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BreachRuleServiceImpl implements BreachRuleService {

    private final BreachRuleRepository breachRuleRepository;
}
