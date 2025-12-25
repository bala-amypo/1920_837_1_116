package com.example.demo.service;

import com.example.demo.entity.Contract;
import java.util.List;

public interface ContractService {

    Contract createContract(Contract contract);

    List<Contract> getAllContracts();

    Contract getContractById(Long id);

    Contract updateContract(Long id, Contract contract);

    void updateContractStatus(Long id);
}
