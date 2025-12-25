package com.example.demo.repository;

import com.example.demo.entity.Contract;
import java.util.List;
import java.util.Optional;

public interface ContractRepository {

    Contract save(Contract contract);

    List<Contract> findAll();

    Optional<Contract> findById(Long id);

    Optional<Contract> findByContractNumber(String contractNumber);
}
