package com.example.demo.entity;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BreachReport {

    private Long id;
    private Contract contract;
    private int daysDelayed;
    private BigDecimal penaltyAmount;
}
