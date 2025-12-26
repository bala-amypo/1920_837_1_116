package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.service.ContractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public Contract createContract(Contract contract) {

        // ✅ primitive validation (NO null check)
        if (contract.getBaseContractValue() <= 0) {
            throw new BadRequestException("Base contract value must be positive");
        }

        if (contract.getAgreedDeliveryDate() == null) {
            throw new BadRequestException("Agreed delivery date is required");
        }

        return contractRepository.save(contract);
    }

    @Override
    public Contract updateContract(Long id, Contract contract) {

        Contract existing = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        if (contract.getBaseContractValue() <= 0) {
            throw new BadRequestException("Base contract value must be positive");
        }

        existing.setClientName(contract.getClientName());
        existing.setBaseContractValue(contract.getBaseContractValue());
        existing.setAgreedDeliveryDate(contract.getAgreedDeliveryDate());

        return contractRepository.save(existing);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }
}
