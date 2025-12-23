package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class VendorTier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String tierName;

    private Integer minScoreThreshold;
    private Boolean active;

    public Long getId() {
        return id;
    }

    public String getTierName() {
        return tierName;
    }

    public Integer getMinScoreThreshold() {
        return minScoreThreshold;
    }

    public Boolean getActive() {
        return active;
    }
}
