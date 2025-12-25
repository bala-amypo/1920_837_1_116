package com.example.demo.entity;

public class BreachReport {

    private Long id;
    private Long contractId;
    private int daysDelayed;
    private double penaltyAmount;

    public BreachReport() {}

    private BreachReport(Builder builder) {
        this.id = builder.id;
        this.contractId = builder.contractId;
        this.daysDelayed = builder.daysDelayed;
        this.penaltyAmount = builder.penaltyAmount;
    }

    // ✅ REQUIRED GETTERS (used by test cases)
    public Long getId() {
        return id;
    }

    public Long getContractId() {
        return contractId;
    }

    public int getDaysDelayed() {
        return daysDelayed;
    }

    public double getPenaltyAmount() {
        return penaltyAmount;
    }

    // ✅ BUILDER (testcases depend on this)
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Long contractId;
        private int daysDelayed;
        private double penaltyAmount;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder contractId(Long contractId) {
            this.contractId = contractId;
            return this;
        }

        public Builder daysDelayed(int daysDelayed) {
            this.daysDelayed = daysDelayed;
            return this;
        }

        public Builder penaltyAmount(double penaltyAmount) {
            this.penaltyAmount = penaltyAmount;
            return this;
        }

        public BreachReport build() {
            return new BreachReport(this);
        }
    }
}
