package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.repository.ContractRepository;
import com.example.demo.service.ContractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository repository;

    public ContractServiceImpl(ContractRepository repository) {
        this.repository = repository;
    }

    @Override
    public Contract createContract(Contract contract) {
        return repository.save(contract);
    }

    @Override
    public List<Contract> getAllContracts() {
        return repository.findAll();
    }

    @Override
    public Contract updateContractStatus(Long id) {
        Contract c = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
        c.setActive(false);
        return repository.save(c);
    }
}
