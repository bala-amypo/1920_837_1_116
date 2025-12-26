package com.example.demo.repository;

import com.example.demo.entity.PenaltyCalculation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PenaltyCalculationRepository extends JpaRepository<PenaltyCalculation, Long> {

    Optional<PenaltyCalculation> findTopByContract_IdOrderByCalculatedDateDesc(Long contractId);
}
