package com.example.demo.repository;

public interface BreachReportRepository extends JpaRepository<BreachReport, Long> {
    List<BreachReport> findByContractId(Long contractId);
}
