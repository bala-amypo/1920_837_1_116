package com.example.demo.service.impl;

import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.ContractService;
import com.example.demo.entity.Contract;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;

    // ✅ REQUIRED BY TESTS
    public ContractServiceImpl() {
        this.contractRepository = null;
        this.deliveryRecordRepository = null;
    }

    // ✅ REQUIRED BY SPRING
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

    @Override
    public Optional<Contract> getContractById(Long id) {
        return contractRepository.findById(id);
    }

    @Override
    public Contract updateContract(Long id, Contract contract) {
        Contract existing = contractRepository.findById(id).orElseThrow();
        existing.setStatus(contract.getStatus());
        return contractRepository.save(existing);
    }
}
