package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    public Contract() {}

    // ✅ REQUIRED BY TESTS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // ✅ BUILDER
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Contract c = new Contract();

        public Builder id(Long id) {
            c.id = id;
            return this;
        }

        public Builder status(String status) {
            c.status = status;
            return this;
        }

        public Contract build() {
            return c;
        }
    }
}
