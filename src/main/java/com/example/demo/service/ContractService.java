package com.example.demo.service;

import com.example.demo.entity.Contract;

public interface ContractService {
    Contract createContract(Contract contract);
    Contract updateContract(Long id, Contract contract);
    void updateContractStatus(Long id);
}
