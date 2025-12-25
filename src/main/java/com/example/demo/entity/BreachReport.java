package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "breach_reports")
public class BreachReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Contract contract;

    private Integer daysDelayed;
    private BigDecimal penaltyAmount;
    private String reportStatus;

    // ---------- Constructors ----------
    public BreachReport() {}

    // ---------- Getters & Setters ----------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Contract getContract() { return contract; }
    public void setContract(Contract contract) { this.contract = contract; }

    public Integer getDaysDelayed() { return daysDelayed; }
    public void setDaysDelayed(Integer daysDelayed) { this.daysDelayed = daysDelayed; }

    public BigDecimal getPenaltyAmount() { return penaltyAmount; }
    public void setPenaltyAmount(BigDecimal penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public String getReportStatus() { return reportStatus; }
    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    // ---------- BUILDER ----------
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final BreachReport report = new BreachReport();

        public Builder id(Long id) {
            report.setId(id);
            return this;
        }

        public Builder contract(Contract contract) {
            report.setContract(contract);
            return this;
        }

        public Builder daysDelayed(Integer daysDelayed) {
            report.setDaysDelayed(daysDelayed);
            return this;
        }

        public Builder penaltyAmount(BigDecimal penaltyAmount) {
            report.setPenaltyAmount(penaltyAmount);
            return this;
        }

        public Builder reportStatus(String reportStatus) {
            report.setReportStatus(reportStatus);
            return this;
        }

        public BreachReport build() {
            return report;
        }
    }
}
