package com.example.demo.service.impl;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final DeliveryRecordRepository deliveryRecordRepository;
    private final ContractRepository contractRepository;

    // ✅ REQUIRED BY TESTS
    public DeliveryRecordServiceImpl() {
        this.deliveryRecordRepository = null;
        this.contractRepository = null;
    }

    // ✅ REQUIRED BY SPRING
    public DeliveryRecordServiceImpl(DeliveryRecordRepository deliveryRecordRepository,
                                     ContractRepository contractRepository) {
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.contractRepository = contractRepository;
    }

    @Override
    public DeliveryRecord createRecord(DeliveryRecord record) {
        return deliveryRecordRepository.save(record);
    }

    @Override
    public List<DeliveryRecord> getAllRecords() {
        return deliveryRecordRepository.findAll();
    }
}
