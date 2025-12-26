package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.ContractService;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;

    // Required by tests
    public ContractServiceImpl() {
    }

    // Required by Spring
    public ContractServiceImpl(ContractRepository contractRepository,
                               DeliveryRecordRepository deliveryRecordRepository) {
        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
    }

    @Override
    public Contract createContract(Contract contract) {

        if (contract.getBaseContractValue() == null ||
                contract.getBaseContractValue().signum() <= 0) {
            throw new BadRequestException("Base contract value must be positive");
        }

        contractRepository.findByContractNumber(contract.getContractNumber())
                .ifPresent(c -> {
                    throw new BadRequestException("Contract already exists");
                });

        return contractRepository.save(contract);
    }

    // ✅ MISSING METHOD — FIXED
    @Override
    public Contract updateContract(Long id, Contract updated) {

        Contract existing = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        existing.setTitle(updated.getTitle());
        existing.setCounterpartyName(updated.getCounterpartyName());
        existing.setAgreedDeliveryDate(updated.getAgreedDeliveryDate());
        existing.setBaseContractValue(updated.getBaseContractValue());

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

        Contract c = getContractById(id);

        deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(id)
                .ifPresentOrElse(dr -> {
                    if (dr.getDeliveryDate().isAfter(c.getAgreedDeliveryDate())) {
                        c.setStatus("BREACHED");
                    } else {
                        c.setStatus("COMPLETED");
                    }
                }, () -> {
                    if (LocalDate.now().isBefore(c.getAgreedDeliveryDate())) {
                        c.setStatus("ACTIVE");
                    }
                });

        contractRepository.save(c);
    }
}
