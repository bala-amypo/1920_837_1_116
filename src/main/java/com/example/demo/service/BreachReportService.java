package com.example.demo.service;

import com.example.demo.entity.BreachReport;
import java.util.List;

public interface BreachReportService {

    List<BreachReport> getReportsForContract(Long contractId);
}
