package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contractNumber;

    @Temporal(TemporalType.DATE)
    private Date agreedDeliveryDate;

    private BigDecimal baseContractValue;

    public Long getId() {
        return id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public Date getAgreedDeliveryDate() {
        return agreedDeliveryDate;
    }

    public BigDecimal getBaseContractValue() {
        return baseContractValue;
    }
}
