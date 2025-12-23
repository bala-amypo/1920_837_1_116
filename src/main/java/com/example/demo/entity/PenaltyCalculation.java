package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Contract contract;

    @ManyToOne
    private DeliveryRecord deliveryRecord;

    @ManyToOne
    private BreachRule breachRule;

    private Integer daysDelayed;

    private BigDecimal calculatedPenalty;

    private LocalDateTime calculatedAt;

    public PenaltyCalculation() {
        this.calculatedAt = LocalDateTime.now();
    }

    // ===== GETTERS =====
    public Long getId() {
        return id;
    }

    public Contract getContract() {
        return contract;
    }

    public DeliveryRecord getDeliveryRecord() {
        return deliveryRecord;
    }

    public BreachRule getBreachRule() {
        return breachRule;
    }

    public Integer getDaysDelayed() {
        return daysDelayed;
    }

    public BigDecimal getCalculatedPenalty() {
        return calculatedPenalty;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    // ===== SETTERS =====
    public void setId(Long id) {
        this.id = id;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public void setDeliveryRecord(DeliveryRecord deliveryRecord) {
        this.deliveryRecord = deliveryRecord;
    }

    public void setBreachRule(BreachRule breachRule) {
        this.breachRule = breachRule;
    }

    public void setDaysDelayed(Integer daysDelayed) {
        this.daysDelayed = daysDelayed;
    }

    public void setCalculatedPenalty(BigDecimal calculatedPenalty) {
        this.calculatedPenalty = calculatedPenalty;
    }

    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}
