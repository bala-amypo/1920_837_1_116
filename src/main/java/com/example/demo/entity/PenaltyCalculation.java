package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class PenaltyCalculation {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int daysDelayed;
  private double calculatedPenalty;

  @ManyToOne private Contract contract;
  @ManyToOne private DeliveryRecord deliveryRecord;
  @ManyToOne private BreachRule breachRule;

  public PenaltyCalculation() {}

  public Long getId() { return id; }
  public int getDaysDelayed() { return daysDelayed; }
  public void setDaysDelayed(int d) { this.daysDelayed = d; }
  public double getCalculatedPenalty() { return calculatedPenalty; }
  public void setCalculatedPenalty(double p) { this.calculatedPenalty = p; }
  public Contract getContract() { return contract; }
  public void setContract(Contract c) { this.contract = c; }
  public DeliveryRecord getDeliveryRecord() { return deliveryRecord; }
  public void setDeliveryRecord(DeliveryRecord r) { this.deliveryRecord = r; }
  public BreachRule getBreachRule() { return breachRule; }
  public void setBreachRule(BreachRule b) { this.breachRule = b; }
}
