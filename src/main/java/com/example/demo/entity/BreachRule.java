package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachRule {

    @Id
    @GeneratedValue
    private Long id;

    private String ruleName;

    private BigDecimal penaltyPerDay;

    @Builder.Default
    private Boolean active = true;
}
