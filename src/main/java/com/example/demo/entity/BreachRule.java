package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "breach_rules")
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal penaltyPerDay;

    private Double maxPenaltyPercentage;

    private boolean active;

    // ---------- constructors ----------
    public BreachRule() {}

    public BreachRule(Long id, BigDecimal penaltyPerDay, Double maxPenaltyPercentage, boolean active) {
        this.id = id;
        this.penaltyPerDay = penaltyPerDay;
        this.maxPenaltyPercentage = maxPenaltyPercentage;
        this.active = active;
    }

    // ---------- builder ----------
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private BigDecimal penaltyPerDay;
        private Double maxPenaltyPercentage;
        private boolean active;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder penaltyPerDay(BigDecimal penaltyPerDay) {
            this.penaltyPerDay = penaltyPerDay;
            return this;
        }

        public Builder maxPenaltyPercentage(Double maxPenaltyPercentage) {
            this.maxPenaltyPercentage = maxPenaltyPercentage;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public BreachRule build() {
            return new BreachRule(id, penaltyPerDay, maxPenaltyPercentage, active);
        }
    }

    // ---------- getters & setters ----------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BigDecimal getPenaltyPerDay() { return penaltyPerDay; }
    public void setPenaltyPerDay(BigDecimal penaltyPerDay) { this.penaltyPerDay = penaltyPerDay; }

    public Double getMaxPenaltyPercentage() { return maxPenaltyPercentage; }
    public void setMaxPenaltyPercentage(Double maxPenaltyPercentage) {
        this.maxPenaltyPercentage = maxPenaltyPercentage;
    }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
