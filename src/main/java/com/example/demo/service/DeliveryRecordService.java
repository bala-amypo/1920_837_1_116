package com.example.demo.service;

import com.example.demo.dto.DeliveryRecordDto;
import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class DeliveryRecordService {

    private final DeliveryRecordRepository deliveryRecordRepository;
    private final ContractRepository contractRepository;

    public DeliveryRecordService(DeliveryRecordRepository deliveryRecordRepository,
                                 ContractRepository contractRepository) {
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.contractRepository = contractRepository;
    }

    public DeliveryRecord addDeliveryRecord(DeliveryRecordDto dto) {

        if (dto.getDeliveryDate().after(new Date())) {
            throw new BadRequestException("Delivery date cannot be future");
        }

        Contract contract = contractRepository
                .findByContractNumber(dto.getContractNumber())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contract not found"));

        DeliveryRecord record = new DeliveryRecord();
        record.setContract(contract);
        record.setDeliveryDate(dto.getDeliveryDate());
        record.setNotes(dto.getNotes());
        record.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        return deliveryRecordRepository.save(record);
    }
}
