package com.example.demo.service.impl;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private DeliveryRecordRepository deliveryRecordRepository;

    public DeliveryRecordServiceImpl() {}

    public DeliveryRecordServiceImpl(DeliveryRecordRepository repo) {
        this.deliveryRecordRepository = repo;
    }

    @Override
    public DeliveryRecord createDeliveryRecord(DeliveryRecord record) {
        if (record.getDeliveryDate().isAfter(LocalDate.now())) {
            throw new BadRequestException("Delivery date cannot be in the future");
        }
        return deliveryRecordRepository.save(record);
    }

    @Override
    public List<DeliveryRecord> getDeliveryRecordsForContract(Long contractId) {
        return deliveryRecordRepository.findByContractId(contractId);
    }
}
    