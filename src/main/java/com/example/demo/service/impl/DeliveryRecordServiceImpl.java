package com.example.demo.service.impl;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private DeliveryRecordRepository deliveryRecordRepository;
    private ContractRepository contractRepository;

    public DeliveryRecordServiceImpl() {}

    public DeliveryRecordServiceImpl(
            DeliveryRecordRepository deliveryRecordRepository,
            ContractRepository contractRepository) {
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.contractRepository = contractRepository;
    }

    @Override
    public DeliveryRecord createDeliveryRecord(DeliveryRecord record) {
        return deliveryRecordRepository.save(record);
    }

    @Override
    public DeliveryRecord getRecordById(Long id) {
        return deliveryRecordRepository.findById(id).orElse(null);
    }

    @Override
    public List<DeliveryRecord> getDeliveryRecordsForContract(Long contractId) {
        return deliveryRecordRepository.findByContractId(contractId);
    }

    @Override
    public DeliveryRecord getLatestDeliveryRecord(Long contractId) {
        return deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId);
    }
}
