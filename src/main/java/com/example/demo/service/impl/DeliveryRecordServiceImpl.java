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

    public DeliveryRecordServiceImpl() {
    }

    public DeliveryRecordServiceImpl(DeliveryRecordRepository deliveryRecordRepository) {
        this.deliveryRecordRepository = deliveryRecordRepository;
    }

    @Override
    public DeliveryRecord createDeliveryRecord(DeliveryRecord record) {

        if (record.getDeliveryDate().isAfter(LocalDate.now())) {
            throw new BadRequestException("Delivery date cannot be in the future");
        }

        return deliveryRecordRepository.save(record);
    }

    @Override
    public DeliveryRecord getLatestDeliveryRecord(Long contractId) {
        return deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("No delivery record found"));
    }

    @Override
    public List<DeliveryRecord> getRecordsForContract(Long contractId) {
        return deliveryRecordRepository.findByContractId(contractId);
    }
}
