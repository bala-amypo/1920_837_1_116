package com.example.demo.service;

import com.example.demo.entity.DeliveryRecord;

public interface DeliveryRecordService {

    DeliveryRecord createDeliveryRecord(DeliveryRecord record);

    DeliveryRecord getRecordById(Long id);

    DeliveryRecord getLatestDeliveryRecord(Long contractId);
}
