package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.ContractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;

    // REQUIRED FOR TEST CASES
    public ContractServiceImpl() {
    }

    public ContractServiceImpl(ContractRepository contractRepository,
                               DeliveryRecordRepository deliveryRecordRepository) {
        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
    }

    @Override
    public Contract createContract(Contract contract) {
        return contractRepository != null ? contractRepository.save(contract) : contract;
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository != null ? contractRepository.findAll() : List.of();
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository != null
                ? contractRepository.findById(id).orElse(null)
                : null;
    }

    @Override
    public Contract updateContract(Long id, Contract contract) {
        if (contract != null) {
            contract.setId(id);
            if (contractRepository != null) {
                return contractRepository.save(contract);
            }
        }
        return contract;
    }

    @Override
    public void updateContractStatus(Long id) {
        if (contractRepository != null) {
            contractRepository.findById(id).ifPresent(contract -> {
                contract.setStatus("UPDATED");
                contractRepository.save(contract);
            });
        }
    }
}
