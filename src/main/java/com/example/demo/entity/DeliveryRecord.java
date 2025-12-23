package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class DeliveryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Contract contract;

    private LocalDate deliveryDate;

    private String notes;

    public DeliveryRecord() {}

    // ===== GETTERS =====
    public Long getId() {
        return id;
    }

    public Contract getContract() {
        return contract;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public String getNotes() {
        return notes;
    }

    // ===== SETTERS =====
    public void setId(Long id) {
        this.id = id;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
