package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String contractNumber;
    private String counterpartyName;

    private BigDecimal baseContractValue;

    private LocalDate agreedDeliveryDate;

    @Builder.Default
    private String status = "ACTIVE";
}
