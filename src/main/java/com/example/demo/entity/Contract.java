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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contractNumber;

    private String title;

    private String counterpartyName;

    private BigDecimal baseContractValue;

    private LocalDate agreedDeliveryDate;

    private String status;

    private boolean active = true;
}
