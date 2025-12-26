package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "penalty_calculations")
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Contract contract;

    @ManyToOne
    private DeliveryRecord deliveryRecord;

    @ManyToOne(optional = false)
    private BreachRule breachRule;

    @Column(nullable = false)
    private Integer daysDelayed;

    @Column(nullable = false)
    private BigDecimal calculatedPenalty;

    @Column(updatable = false)
    private LocalDateTime calculatedAt;

    public PenaltyCalculation() {}

    @PrePersist
    protected void onCreate() {
        this.calculatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Contract getContract() { return contract; }
    public void setContract(Contract contract) { this.contract = contract; }
    public DeliveryRecord getDeliveryRecord() { return deliveryRecord; }
    public void setDeliveryRecord(DeliveryRecord deliveryRecord) { this.deliveryRecord = deliveryRecord; }
    public BreachRule getBreachRule() { return breachRule; }
    public void setBreachRule(BreachRule breachRule) { this.breachRule = breachRule; }
    public Integer getDaysDelayed() { return daysDelayed; }
    public void setDaysDelayed(Integer daysDelayed) { this.daysDelayed = daysDelayed; }
    public BigDecimal getCalculatedPenalty() { return calculatedPenalty; }
    public void setCalculatedPenalty(BigDecimal calculatedPenalty) { this.calculatedPenalty = calculatedPenalty; }
    public LocalDateTime getCalculatedAt() { return calculatedAt; }
}
