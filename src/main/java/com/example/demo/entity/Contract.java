package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String contractNumber;

    private String title;
    private String counterpartyName;

    // 🔴 MUST BE LocalDate (NOT Date)
    private LocalDate agreedDeliveryDate;

    private BigDecimal baseContractValue;

    private String status = "ACTIVE";

    public Contract() {}

    // ===== GETTERS & SETTERS =====
    public LocalDate getAgreedDeliveryDate() {
        return agreedDeliveryDate;
    }

    public void setAgreedDeliveryDate(LocalDate agreedDeliveryDate) {
        this.agreedDeliveryDate = agreedDeliveryDate;
    }

    public BigDecimal getBaseContractValue() {
        return baseContractValue;
    }

    public void setBaseContractValue(BigDecimal baseContractValue) {
        this.baseContractValue = baseContractValue;
    }

    // other getters/setters
}
