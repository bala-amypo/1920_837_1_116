package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class BreachReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long contractId;
    private Integer daysDelayed;
    private Double penaltyAmount;

    public BreachReport() {}

    // ✅ GETTERS & SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getContractId() { return contractId; }
    public void setContractId(Long contractId) { this.contractId = contractId; }

    public Integer getDaysDelayed() { return daysDelayed; }
    public void setDaysDelayed(Integer daysDelayed) { this.daysDelayed = daysDelayed; }

    public Double getPenaltyAmount() { return penaltyAmount; }
    public void setPenaltyAmount(Double penaltyAmount) { this.penaltyAmount = penaltyAmount; }

    // ✅ MANUAL BUILDER (REQUIRED FOR TESTS)
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final BreachReport report = new BreachReport();

        public Builder contractId(Long contractId) {
            report.contractId = contractId;
            return this;
        }

        public Builder daysDelayed(Integer daysDelayed) {
            report.daysDelayed = daysDelayed;
            return this;
        }

        public Builder penaltyAmount(Double penaltyAmount) {
            report.penaltyAmount = penaltyAmount;
            return this;
        }

        public BreachReport build() {
            return report;
        }
    }
}
