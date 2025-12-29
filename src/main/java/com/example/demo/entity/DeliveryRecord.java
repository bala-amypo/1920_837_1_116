package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "delivery_records")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Contract contract;

    @Column(nullable = false)
    private LocalDate deliveryDate;

    private String notes;
}
