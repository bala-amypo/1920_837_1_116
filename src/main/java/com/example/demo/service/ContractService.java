package com.example.demo.service;

import com.example.demo.entity.Contract;

public interface ContractService {

    Contract getContractById(Long id);

    void updateContract(Long id, Contract contract);
}
