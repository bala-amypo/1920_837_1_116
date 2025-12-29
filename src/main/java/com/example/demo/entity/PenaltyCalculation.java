package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int delayDays;

    private BigDecimal penaltyAmount;

    @ManyToOne
    private Contract contract;

    @ManyToOne
    private BreachRule breachRule;
}
