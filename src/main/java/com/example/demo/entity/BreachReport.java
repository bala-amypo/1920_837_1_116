package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "breach_reports")
public class BreachReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long contractId;
    private Integer daysDelayed;
    private BigDecimal penaltyAmount;
    private String reportStatus;

    public BreachReport() {}

    public BreachReport(Long id, Long contractId, Integer daysDelayed,
                        BigDecimal penaltyAmount, String reportStatus) {
        this.id = id;
        this.contractId = contractId;
        this.daysDelayed = daysDelayed;
        this.penaltyAmount = penaltyAmount;
        this.reportStatus = reportStatus;
    }

    // ---------- builder ----------
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Long contractId;
        private Integer daysDelayed;
        private BigDecimal penaltyAmount;
        private String reportStatus;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder contractId(Long contractId) {
            this.contractId = contractId;
            return this;
        }

        public Builder daysDelayed(Integer daysDelayed) {
            this.daysDelayed = daysDelayed;
            return this;
        }

        public Builder penaltyAmount(BigDecimal penaltyAmount) {
            this.penaltyAmount = penaltyAmount;
            return this;
        }

        public Builder reportStatus(String reportStatus) {
            this.reportStatus = reportStatus;
            return this;
        }

        public BreachReport build() {
            return new BreachReport(id, contractId, daysDelayed, penaltyAmount, reportStatus);
        }
    }

    // ---------- getters & setters ----------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getContractId() { return contractId; }
    public void setContractId(Long contractId) { this.contractId = contractId; }

    public Integer getDaysDelayed() { return daysDelayed; }
    public void setDaysDelayed(Integer daysDelayed) { this.daysDelayed = daysDelayed; }

    public BigDecimal getPenaltyAmount() { return penaltyAmount; }
    public void setPenaltyAmount(BigDecimal penaltyAmount) { this.penaltyAmount = penaltyAmount; }

    public String getReportStatus() { return reportStatus; }
    public void setReportStatus(String reportStatus) { this.reportStatus = reportStatus; }
}
