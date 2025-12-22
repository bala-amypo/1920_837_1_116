package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class DeliveryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Contract contract;

    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    public Date getDeliveryDate() {
        return deliveryDate;
    }
}
