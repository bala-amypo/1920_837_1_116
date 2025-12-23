package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.ContractService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;

    public ContractServiceImpl(
            ContractRepository contractRepository,
            DeliveryRecordRepository deliveryRecordRepository) {

        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
    }

    @Override
    public Contract createContract(Contract contract) {

        if (contractRepository.findByContractNumber(contract.getContractNumber()).isPresent()) {
            throw new BadRequestException("Contract already exists");
        }

        if (contract.getBaseContractValue() == null ||
                contract.getBaseContractValue().signum() <= 0) {
            throw new BadRequestException("Base contract value invalid");
        }

        if (contract.getAgreedDeliveryDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("Agreed delivery date must be in future");
        }

        contract.setStatus("ACTIVE");
        return contractRepository.save(contract);
    }

    @Override
    public Contract updateContract(Long id, Contract contract) {

        Contract existing = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        existing.setTitle(contract.getTitle());
        existing.setCounterpartyName(contract.getCounterpartyName());
        existing.setAgreedDeliveryDate(contract.getAgreedDeliveryDate());
        existing.setBaseContractValue(contract.getBaseContractValue());

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

    @Override
    public void updateContractStatus(Long id) {

        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        List<DeliveryRecord> records =
                deliveryRecordRepository.findByContractId(id);

        if (records.isEmpty()) {
            return;
        }

        DeliveryRecord latest =
                deliveryRecordRepository
                        .findFirstByContractIdOrderByDeliveryDateDesc(id)
                        .orElse(null);

        if (latest != null &&
                latest.getDeliveryDate()
                        .isAfter(contract.getAgreedDeliveryDate())) {

            contract.setStatus("BREACHED");
        } else {
            contract.setStatus("COMPLETED");
        }

        contractRepository.save(contract);
    }
}
