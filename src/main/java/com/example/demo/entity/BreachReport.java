package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "breach_reports")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Contract contract;

    @Column(nullable = false)
    private Integer daysDelayed;

    @Column(nullable = false)
    private BigDecimal penaltyAmount;

    @Builder.Default
    private String reportStatus = "GENERATED";

    @Column(nullable = false, updatable = false)
    private LocalDateTime generatedAt;

    @PrePersist
    public void onGenerate() {
        this.generatedAt = LocalDateTime.now();
    }
}
