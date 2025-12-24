package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Contract contract;

    private Integer daysDelayed;
    private BigDecimal calculatedPenalty;
    private LocalDateTime calculatedAt;

    // ===== GETTERS =====
    public Long getId() { return id; }

    public Contract getContract() { return contract; }
    public Integer getDaysDelayed() { return daysDelayed; }
    public BigDecimal getCalculatedPenalty() { return calculatedPenalty; }
    public LocalDateTime getCalculatedAt() { return calculatedAt; }

    // ===== SETTERS =====
    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public void setDaysDelayed(Integer daysDelayed) {
        this.daysDelayed = daysDelayed;
    }

    public void setCalculatedPenalty(BigDecimal calculatedPenalty) {
        this.calculatedPenalty = calculatedPenalty;
    }

    // ✅ THIS IS THE MISSING METHOD (FIX)
    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }

    // ===== BUILDER (used by tests) =====
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final PenaltyCalculation p = new PenaltyCalculation();

        public Builder id(Long id) {
            p.id = id;
            return this;
        }

        public Builder contract(Contract contract) {
            p.contract = contract;
            return this;
        }

        public Builder daysDelayed(Integer daysDelayed) {
            p.daysDelayed = daysDelayed;
            return this;
        }

        public Builder calculatedPenalty(BigDecimal penalty) {
            p.calculatedPenalty = penalty;
            return this;
        }

        public Builder calculatedAt(LocalDateTime t) {
            p.calculatedAt = t;
            return this;
        }

        public PenaltyCalculation build() {
            return p;
        }
    }
}
