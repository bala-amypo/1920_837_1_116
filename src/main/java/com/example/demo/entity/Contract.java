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

    private LocalDate agreedDeliveryDate;

    private BigDecimal baseContractValue;

    private String status = "ACTIVE";

    public Contract() {}

    // ===== GETTERS =====
    public Long getId() {
        return id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getCounterpartyName() {
        return counterpartyName;
    }

    public LocalDate getAgreedDeliveryDate() {
        return agreedDeliveryDate;
    }

    public BigDecimal getBaseContractValue() {
        return baseContractValue;
    }

    public String getStatus() {
        return status;
    }

    // ===== SETTERS =====
    public void setId(Long id) {
        this.id = id;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCounterpartyName(String counterpartyName) {
        this.counterpartyName = counterpartyName;
    }

    public void setAgreedDeliveryDate(LocalDate agreedDeliveryDate) {
        this.agreedDeliveryDate = agreedDeliveryDate;
    }

    public void setBaseContractValue(BigDecimal baseContractValue) {
        this.baseContractValue = baseContractValue;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
