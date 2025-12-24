package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String contractNumber;
    private String title;
    private String counterpartyName;
    private LocalDate agreedDeliveryDate;
    private BigDecimal baseContractValue;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ===== GETTERS & SETTERS =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContractNumber() { return contractNumber; }
    public void setContractNumber(String contractNumber) { this.contractNumber = contractNumber; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCounterpartyName() { return counterpartyName; }
    public void setCounterpartyName(String counterpartyName) { this.counterpartyName = counterpartyName; }

    public LocalDate getAgreedDeliveryDate() { return agreedDeliveryDate; }
    public void setAgreedDeliveryDate(LocalDate agreedDeliveryDate) { this.agreedDeliveryDate = agreedDeliveryDate; }

    public BigDecimal getBaseContractValue() { return baseContractValue; }
    public void setBaseContractValue(BigDecimal baseContractValue) { this.baseContractValue = baseContractValue; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // ===== BUILDER =====
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final Contract c = new Contract();
        public Builder id(Long id){ c.setId(id); return this; }
        public Builder contractNumber(String v){ c.setContractNumber(v); return this; }
        public Builder title(String v){ c.setTitle(v); return this; }
        public Builder counterpartyName(String v){ c.setCounterpartyName(v); return this; }
        public Builder agreedDeliveryDate(LocalDate v){ c.setAgreedDeliveryDate(v); return this; }
        public Builder baseContractValue(BigDecimal v){ c.setBaseContractValue(v); return this; }
        public Builder status(String v){ c.setStatus(v); return this; }
        public Contract build(){ return c; }
    }
}
