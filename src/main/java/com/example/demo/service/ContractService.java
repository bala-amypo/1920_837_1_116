package com.example.demo.service;

import com.example.demo.dto.ContractDto;
import com.example.demo.entity.Contract;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public Contract createContract(ContractDto dto) {

        Contract contract = new Contract();
        contract.setContractNumber(dto.getContractNumber());
        contract.setTitle(dto.getTitle());
        contract.setCounterpartyName(dto.getCounterpartyName());
        contract.setAgreedDeliveryDate(dto.getAgreedDeliveryDate());
        contract.setBaseContractValue(dto.getBaseContractValue());
        contract.setStatus("ACTIVE");
        contract.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        contract.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return contractRepository.save(contract);
    }

    public Contract getByContractNumber(String contractNumber) {
        return contractRepository.findByContractNumber(contractNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contract not found"));
    }
}
