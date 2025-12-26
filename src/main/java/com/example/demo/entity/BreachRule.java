package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class BreachRule {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private double penaltyPerDay;
  private int maxPenaltyPercentage;
  private Boolean active = true;
  private Boolean isDefaultRule = false;

  public BreachRule() {}

  public Long getId() { return id; }
  public double getPenaltyPerDay() { return penaltyPerDay; }
  public void setPenaltyPerDay(double p) { this.penaltyPerDay = p; }
  public int getMaxPenaltyPercentage() { return maxPenaltyPercentage; }
  public void setMaxPenaltyPercentage(int m) { this.maxPenaltyPercentage = m; }
  public Boolean getActive() { return active; }
  public void setActive(Boolean a) { this.active = a; }
  public Boolean getIsDefaultRule() { return isDefaultRule; }
  public void setIsDefaultRule(Boolean d) { this.isDefaultRule = d; }
}
