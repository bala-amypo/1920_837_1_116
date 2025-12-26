package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Contract {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private double baseContractValue;
  private LocalDate agreedDeliveryDate;
  private String status;

  public Contract() {}

  public Long getId() { return id; }
  public double getBaseContractValue() { return baseContractValue; }
  public void setBaseContractValue(double v) { this.baseContractValue = v; }
  public LocalDate getAgreedDeliveryDate() { return agreedDeliveryDate; }
  public void setAgreedDeliveryDate(LocalDate d) { this.agreedDeliveryDate = d; }
  public String getStatus() { return status; }
  public void setStatus(String s) { this.status = s; }
}
