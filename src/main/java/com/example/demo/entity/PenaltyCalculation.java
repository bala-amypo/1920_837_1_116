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

    public Long getId() { return id; }
    public Contract getContract() { return contract; }
    public void setContract(Contract c) { this.contract = c; }

    public Integer getDaysDelayed() { return daysDelayed; }
    public void setDaysDelayed(Integer d) { this.daysDelayed = d; }

    public BigDecimal getCalculatedPenalty() { return calculatedPenalty; }
    public void setCalculatedPenalty(BigDecimal p) { this.calculatedPenalty = p; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PenaltyCalculation p = new PenaltyCalculation();
        public Builder id(Long id){ p.id = id; return this; }
        public Builder contract(Contract c){ p.contract = c; return this; }
        public Builder daysDelayed(Integer d){ p.daysDelayed = d; return this; }
        public Builder calculatedPenalty(BigDecimal b){ p.calculatedPenalty = b; return this; }
        public PenaltyCalculation build(){ return p; }
    }
}
