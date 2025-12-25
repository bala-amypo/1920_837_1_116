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

    public DeliveryRecordServiceImpl() {
    }

    public DeliveryRecordServiceImpl(DeliveryRecordRepository deliveryRecordRepository,
                                     ContractRepository contractRepository) {
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.contractRepository = contractRepository;
    }

    @Override
    public DeliveryRecord getRecordById(Long id) {
        return deliveryRecordRepository != null
                ? deliveryRecordRepository.findById(id).orElse(null)
                : null;
    }

    @Override
    public List<DeliveryRecord> getDeliveryRecordsForContract(Long contractId) {
        return deliveryRecordRepository != null
                ? deliveryRecordRepository.findByContractId(contractId)
                : List.of();
    }

    @Override
    public DeliveryRecord getLatestDeliveryRecord(Long contractId) {
        // ✅ NO orElse() HERE
        return deliveryRecordRepository != null
                ? deliveryRecordRepository.findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                : null;
    }
}
