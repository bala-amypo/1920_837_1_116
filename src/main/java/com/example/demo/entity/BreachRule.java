package com.example.demo.entity;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BreachRule {

    private Long id;
    private String ruleName;
    private Double maxPenaltyPercentage;
    private Boolean active;
    private Boolean defaultRule;
}
