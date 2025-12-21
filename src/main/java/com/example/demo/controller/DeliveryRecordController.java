package com.example.demo.controller;

import com.example.demo.dto.DeliveryRecordDto;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryRecordController {

    private final DeliveryRecordService deliveryRecordService;

    public DeliveryRecordController(DeliveryRecordService deliveryRecordService) {
        this.deliveryRecordService = deliveryRecordService;
    }

    @PostMapping
    public ResponseEntity<DeliveryRecord> addDelivery(@RequestBody DeliveryRecordDto dto) {
        return ResponseEntity.ok(deliveryRecordService.addDeliveryRecord(dto));
    }
}
