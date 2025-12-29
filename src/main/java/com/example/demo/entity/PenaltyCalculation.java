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
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Contract contract;

    private int daysDelayed;

    private BigDecimal totalPenalty;
}
