package com.example.demo.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PenaltyCalculationDto {

    private String contractNumber;
    private Integer daysDelayed;
    private BigDecimal calculatedPenalty;
    private Timestamp calculatedAt;

    public PenaltyCalculationDto() {}

    public String getContractNumber() {
        return contractNumber;
    }
 
    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }
 
    public Integer getDaysDelayed() {
        return daysDelayed;
    }
 
    public void setDaysDelayed(Integer daysDelayed) {
        this.daysDelayed = daysDelayed;
    }
 
    public BigDecimal getCalculatedPenalty() {
        return calculatedPenalty;
    }
 
    public void setCalculatedPenalty(BigDecimal calculatedPenalty) {
        this.calculatedPenalty = calculatedPenalty;
    }
 
    public Timestamp getCalculatedAt() {
        return calculatedAt;
    }
 
    public void setCalculatedAt(Timestamp calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}
