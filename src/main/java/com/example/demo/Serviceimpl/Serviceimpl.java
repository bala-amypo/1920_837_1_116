package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl {

    public ServiceImpl() {
        // constructor injection goes here if needed
    }

    public void validateExists(Object entity) {
        if (entity == null) {
            throw new ResourceNotFoundException("Resour
