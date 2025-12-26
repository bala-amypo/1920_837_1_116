package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ TEST EXPECTS int
    private int daysDelayed;

    // ✅ TEST EXPECTS double
    private double penaltyAmount;

    @ManyToOne
    private Contract contract;
}
