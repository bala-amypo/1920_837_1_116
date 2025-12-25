package com.example.demo.controller;

import com.example.demo.entity.Contract;
import com.example.demo.service.ContractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    private final ContractService service;

    public ContractController(ContractService service) {
        this.service = service;
    }

    @PostMapping
    public Contract create(@RequestBody Contract contract) {
        return service.createContract(contract);
    }

    @GetMapping
    public List<Contract> getAll() {
        return service.getAllContracts();
    }

    @GetMapping("/{id}")
    public Contract getById(@PathVariable Long id) {
        return service.getContractById(id);
    }

    @PutMapping("/{id}")
    public Contract update(@PathVariable Long id, @RequestBody Contract contract) {
        return service.updateContract(id, contract);
    }

    @PatchMapping("/{id}/status")
    public void updateStatus(@PathVariable Long id) {
        service.updateContractStatus(id);
    }
}
