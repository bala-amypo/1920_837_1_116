package com.example.demo.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class BreachReportDto {

    private Long contractId;
    private Integer daysDelayed;
    private BigDecimal penaltyAmount;
    private String remarks;
    private Timestamp reportGeneratedAt;

    public BreachReportDto() {}

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Integer getDaysDelayed() {
        return daysDelayed;
    }

    public void setDaysDelayed(Integer daysDelayed) {
        this.daysDelayed = daysDelayed;
    }

    public BigDecimal getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(BigDecimal penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Timestamp getReportGeneratedAt() {
        return reportGeneratedAt;
    }

    public void setReportGeneratedAt(Timestamp reportGeneratedAt) {
        this.reportGeneratedAt = reportGeneratedAt;
    }
}
