package com.example.demo.repository;

import com.example.demo.entity.DeliveryRecord;
import java.util.List;
import java.util.Optional;

public interface DeliveryRecordRepository {

    Optional<DeliveryRecord> findById(Long id);

    List<DeliveryRecord> findByContractId(Long contractId);

    Optional<DeliveryRecord> findFirstByContractIdOrderByDeliveryDateAsc(Long contractId);
}
