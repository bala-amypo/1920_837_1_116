package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal penaltyAmount;

    @ManyToOne
    private Contract contract;

    @ManyToOne
    private DeliveryRecord deliveryRecord;

    @ManyToOne
    private BreachRule breachRule;

    private int daysDelayed;
private BigDecimal calculatedPenalty;

}
