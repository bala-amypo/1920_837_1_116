package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.service.ContractService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository repo;

    public ContractServiceImpl(ContractRepository repo) {
        this.repo = repo;
    }

    @Override
    public Contract createContract(Contract contract) {

        if (contract.getBaseContractValue().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Contract value must be positive");
        }

        contract.setStatus("ACTIVE");
        return repo.save(contract);
    }
}
