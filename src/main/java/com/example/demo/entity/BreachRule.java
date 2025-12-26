package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;

    // ✅ TEST EXPECTS double
    private double penaltyPerDay;

    // ✅ TEST EXPECTS int
    private int maxPenaltyPercentage;

    @Builder.Default
    private Boolean active = true;

    @Builder.Default
    private Boolean isDefaultRule = false;
}
