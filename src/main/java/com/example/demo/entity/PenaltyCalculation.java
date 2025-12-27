package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int daysDelayed;
    private double calculatedPenalty;

    @ManyToOne
    private Contract contract;

    @ManyToOne
    private DeliveryRecord deliveryRecord;

    @ManyToOne
    private BreachRule breachRule;

    public PenaltyCalculation() {}

    public Long getId() { return id; }

    public int getDaysDelayed() { return daysDelayed; }
    public void setDaysDelayed(int daysDelayed) {
        this.daysDelayed = daysDelayed;
    }

    public double getCalculatedPenalty() { return calculatedPenalty; }
    public void setCalculatedPenalty(double calculatedPenalty) {
        this.calculatedPenalty = calculatedPenalty;
    }

    public Contract getContract() { return contract; }
    public void setContract(Contract contract) { this.contract = contract; }

    public DeliveryRecord getDeliveryRecord() { return deliveryRecord; }
    public void setDeliveryRecord(DeliveryRecord deliveryRecord) {
        this.deliveryRecord = deliveryRecord;
    }

    public BreachRule getBreachRule() { return breachRule; }
    public void setBreachRule(BreachRule breachRule) {
        this.breachRule = breachRule;
    }
}
