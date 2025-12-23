package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class DeliveryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Contract contract;

    @Column(nullable = false)
    private LocalDate deliveryDate;

    private String notes;

    public DeliveryRecord() {}

    public DeliveryRecord(Contract contract, LocalDate deliveryDate) {
        this.contract = contract;
        this.deliveryDate = deliveryDate;
    }

    // getters & setters
}
