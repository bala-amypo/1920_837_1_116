package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(
    name = "breach_rules",
    uniqueConstraints = @UniqueConstraint(columnNames = "ruleName")
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleName;

    @Column(nullable = false)
    private BigDecimal penaltyPerDay;

    @Column(nullable = false)
    private Double maxPenaltyPercentage;

    @Builder.Default
    private Boolean active = true;

    @Builder.Default
    private Boolean isDefaultRule = false;
}
