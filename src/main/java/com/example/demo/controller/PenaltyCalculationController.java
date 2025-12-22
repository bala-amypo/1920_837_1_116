package com.example.demo.controller;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/penalties")
public class PenaltyCalculationController {

    private final PenaltyCalculationService penaltyCalculationService;

    public PenaltyCalculationController(PenaltyCalculationService penaltyCalculationService) {
        this.penaltyCalculationService = penaltyCalculationService;
    }

    @PostMapping("/calculate/{contractNumber}")
    public ResponseEntity<PenaltyCalculation> calculate(
            @PathVariable String contractNumber) {
        return ResponseEntity.ok(
                penaltyCalculationService.calculatePenalty(contractNumber));
    }
}
