package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String ruleName;

    private BigDecimal penaltyPerDay;
    private Double maxPenaltyPercentage;
    private Boolean active;
    private Boolean isDefaultRule;

    // ===== GETTERS / SETTERS =====
    public Long getId() { return id; }
    public String getRuleName() { return ruleName; }
    public BigDecimal getPenaltyPerDay() { return penaltyPerDay; }
    public Double getMaxPenaltyPercentage() { return maxPenaltyPercentage; }
    public Boolean getActive() { return active; }
    public Boolean getIsDefaultRule() { return isDefaultRule; }

    public void setActive(Boolean active) { this.active = active; }

    // ===== BUILDER =====
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final BreachRule r = new BreachRule();

        public Builder id(Long id) { r.id = id; return this; }
        public Builder ruleName(String name) { r.ruleName = name; return this; }
        public Builder penaltyPerDay(BigDecimal p) { r.penaltyPerDay = p; return this; }
        public Builder maxPenaltyPercentage(Double m) { r.maxPenaltyPercentage = m; return this; }
        public Builder active(Boolean a) { r.active = a; return this; }
        public Builder isDefaultRule(Boolean d) { r.isDefaultRule = d; return this; }

        public BreachRule build() { return r; }
    }
}
