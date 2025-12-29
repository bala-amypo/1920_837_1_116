package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.repository.ContractRepository;
import com.example.demo.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository repo;

    public Contract createContract(Contract contract) {
        return repo.save(contract);
    }

    public Contract updateContract(Long id, Contract updated) {
        Contract c = repo.findById(id).orElseThrow();
        c.setTitle(updated.getTitle());
        c.setBaseContractValue(updated.getBaseContractValue());
        c.setStatus(updated.getStatus());
        return repo.save(c);
    }

    public void updateContractStatus(Long id) {
        Contract c = repo.findById(id).orElseThrow();
        c.setStatus("CLOSED");
        repo.save(c);
    }
}
