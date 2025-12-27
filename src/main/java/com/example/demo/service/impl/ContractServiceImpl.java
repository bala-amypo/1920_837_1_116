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

    private ContractRepository contractRepository;

    public ContractServiceImpl() {}

    public ContractServiceImpl(ContractRepository repo) {
        this.contractRepository = repo;
    }

    @Override
    public Contract createContract(Contract contract) {
        if (contract.getBaseContractValue() <= 0) {
            throw new BadRequestException("Base contract value must be greater than zero");
        }
        return contractRepository.save(contract);
    }

    @Override
    public Contract updateContract(Long id, Contract contract) {
        Contract existing = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        existing.setBaseContractValue(contract.getBaseContractValue());
        existing.setAgreedDeliveryDate(contract.getAgreedDeliveryDate());
        return contractRepository.save(existing);
    }

    @Override
    public void updateContractStatus(Long id) {
        Contract c = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));
        c.setStatus("COMPLETED");
        contractRepository.save(c);
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
