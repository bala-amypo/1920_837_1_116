package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.service.ContractService;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository repo;

    public ContractServiceImpl(ContractRepository repo) {
        this.repo = repo;
    }

    @Override
    public Contract createContract(Contract contract) {
        contract.setStatus("ACTIVE");
        return repo.save(contract);
    }

    // ✅ MISSING METHOD
    @Override
    public Contract updateContractStatus(Long id) {
        Contract contract = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));
        contract.setStatus("COMPLETED");
        return repo.save(contract);
    }
}
