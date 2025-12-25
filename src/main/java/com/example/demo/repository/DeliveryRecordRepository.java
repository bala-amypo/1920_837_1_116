package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.DeliveryRecord;
import java.util.List;
import java.util.Optional;

public interface DeliveryRecordRepository extends JpaRepository<DeliveryRecord, Long> {

    List<DeliveryRecord> findByContractIdOrderByDeliveryDateAsc(Long contractId);

    Optional<DeliveryRecord> findFirstByContractIdOrderByDeliveryDateDesc(Long contractId);
}
