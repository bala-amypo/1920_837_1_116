package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer minDelayDays;

    private Integer maxDelayDays;

    private BigDecimal penaltyPercentage;

    private Boolean active;
}
