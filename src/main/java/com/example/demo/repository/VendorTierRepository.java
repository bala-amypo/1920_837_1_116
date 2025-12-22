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

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public String getTierName() {
        return tierName;
    }

    public void setTierName(String tierName) {
        this.tierName = tierName;
    }

    public Integer getMinScoreThreshold() {
        return minScoreThreshold;
    }

    public void setMinScoreThreshold(Integer minScoreThreshold) {
        this.minScoreThreshold = minScoreThreshold;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
