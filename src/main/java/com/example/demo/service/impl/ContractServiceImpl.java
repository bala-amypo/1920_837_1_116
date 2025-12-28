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
        if (contract == null) {
            throw new BadRequestException("Contract cannot be null");
        }
        return contractRepository.save(contract);
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

    @Override
    public Contract updateContract(Long id, Contract updatedContract) {
        Contract existing = getContractById(id);

        existing.setTitle(updatedContract.getTitle());
        existing.setValue(updatedContract.getValue());
        existing.setActive(updatedContract.getActive());

        return contractRepository.save(existing);
    }

    @Override
    public void updateContractStatus(Long id) {
        Contract contract = getContractById(id);
        contract.setActive(false);
        contractRepository.save(contract);
    }

    @Override
    public void deleteContract(Long id) {
        Contract contract = getContractById(id);
        contractRepository.delete(contract);
    }
}
