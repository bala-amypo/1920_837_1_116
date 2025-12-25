package com.example.demo.repository;

import com.example.demo.entity.DeliveryRecord;
import java.util.Optional;

public interface DeliveryRecordRepository {
    Optional<DeliveryRecord> findByContractIdOrderByDeliveryDateAsc(Long contractId);
}
