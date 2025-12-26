package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ TEST EXPECTS int
    private int daysDelayed;

    // ✅ TEST EXPECTS double
    private double calculatedPenalty;

    @ManyToOne
    private Contract contract;

    @ManyToOne
    private DeliveryRecord deliveryRecord;

    @ManyToOne
    private BreachRule breachRule;
}
