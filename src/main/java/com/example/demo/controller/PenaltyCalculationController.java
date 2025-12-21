package com.example.demo.controller;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.exception.BadRequestException;
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

    /**
     * Calculate penalty for a specific contract by contractNumber.
     * 
     * Example: POST /api/penalties/calculate/{contractNumber}
     */
    @PostMapping("/calculate/{contractNumber}")
    public ResponseEntity<PenaltyCalculation> calculatePenalty(@PathVariable String contractNumber) {
        try {
            PenaltyCalculation calculation = penaltyCalculationService.calculatePenalty(contractNumber);
            return ResponseEntity.ok(calculation);
        } catch (BadRequestException e) {
            // Example: "No delivery record" or other validation errors
            throw e;
        } catch (Exception e) {
            // Fallback exception
            throw new BadRequestException(e.getMessage());
        }
    }
}
