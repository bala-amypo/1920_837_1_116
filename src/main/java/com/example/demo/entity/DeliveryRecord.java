package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class DeliveryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Contract contract;

    private LocalDate deliveryDate;
    private String notes;
    private LocalDateTime createdAt;

    // ===== GETTERS / SETTERS =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Contract getContract() { return contract; }
    public void setContract(Contract contract) { this.contract = contract; }

    public LocalDate getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    // ===== BUILDER (REQUIRED BY TESTS) =====
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final DeliveryRecord r = new DeliveryRecord();

        public Builder id(Long id) { r.id = id; return this; }
        public Builder contract(Contract c) { r.contract = c; return this; }
        public Builder deliveryDate(LocalDate d) { r.deliveryDate = d; return this; }
        public Builder notes(String n) { r.notes = n; return this; }

        public DeliveryRecord build() { return r; }
    }
}
