package com.example.demo.service;

import com.example.demo.entity.Contract;
import java.util.List;

public interface ContractService {

    Contract createContract(Contract contract);

    Contract updateContract(Long id, Contract contract);

    void updateContractStatus(Long contractId);

    Contract getContractById(Long id);

    List<Contract> getAllContracts();
}
