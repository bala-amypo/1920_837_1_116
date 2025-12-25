package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.ContractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;

    // Required by tests
    public ContractServiceImpl() {
        this.contractRepository = null;
        this.deliveryRecordRepository = null;
    }

    // Required by Spring
    public ContractServiceImpl(ContractRepository contractRepository,
                               DeliveryRecordRepository deliveryRecordRepository) {
        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
    }

    @Override
    public Contract createContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    // 🔥 FIXED: return Contract (NOT Optional)
    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id).orElse(null);
    }

    // 🔥 REQUIRED METHOD
    @Override
    public void updateContractStatus(Long id) {
        Contract contract = contractRepository.findById(id).orElse(null);
        if (contract != null) {
            contract.setStatus("UPDATED");
            contractRepository.save(contract);
        }
    }
}
