package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class DeliveryRecord {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long contractId;
  private LocalDate deliveryDate;

  public DeliveryRecord() {}

  public Long getId() { return id; }
  public Long getContractId() { return contractId; }
  public void setContractId(Long c) { this.contractId = c; }
  public LocalDate getDeliveryDate() { return deliveryDate; }
  public void setDeliveryDate(LocalDate d) { this.deliveryDate = d; }
}
