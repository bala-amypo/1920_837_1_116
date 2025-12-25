package com.example.demo.repository;

import com.example.demo.entity.Contract;
import java.util.Optional;

public interface ContractRepository {
    Optional<Contract> findByContractNumber(String contractNumber);
}
