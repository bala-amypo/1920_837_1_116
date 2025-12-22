package com.example.demo.entity;

@Entity
public class PenaltyCalculation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Contract contract;

    private Integer daysDelayed;
    private BigDecimal calculatedPenalty;

    @ManyToOne
    private BreachRule appliedRule;

    private Timestamp calculatedAt;
}
