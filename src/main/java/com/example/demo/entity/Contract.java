package com.example.demo.entity;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {

    private Long id;
    private String contractNumber;
    private String counterpartyName;
    private BigDecimal baseContractValue;
    private String status;
}
