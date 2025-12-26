package com.example.demo.controller;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-records")
public class DeliveryRecordController {

    private final DeliveryRecordService service;

    public DeliveryRecordController(DeliveryRecordService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DeliveryRecord> create(@RequestBody DeliveryRecord record) {
        return ResponseEntity.ok(service.createDeliveryRecord(record));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryRecord> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRecordById(id));
    }

    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<DeliveryRecord>> getAll(@PathVariable Long contractId) {
        return ResponseEntity.ok(service.getDeliveryRecordsForContract(contractId));
    }

    @GetMapping("/contract/{contractId}/latest")
    public ResponseEntity<DeliveryRecord> getLatest(@PathVariable Long contractId) {
        return ResponseEntity.ok(service.getLatestDeliveryRecord(contractId));
    }
}
