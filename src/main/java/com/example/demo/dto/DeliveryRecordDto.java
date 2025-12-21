package com.example.demo.dto;

import java.util.Date;

public class DeliveryRecordDto {

    private String contractNumber;
    private Date deliveryDate;
    private String notes;

    public DeliveryRecordDto() {}

    public String getContractNumber() {
        return contractNumber;
    }
 
    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }
 
    public Date getDeliveryDate() {
        return deliveryDate;
    }
 
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
 
    public String getNotes() {
        return notes;
    }
 
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
