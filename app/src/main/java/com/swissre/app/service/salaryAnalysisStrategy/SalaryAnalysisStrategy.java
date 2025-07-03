package com.swissre.app.service.salaryAnalysisStrategy;

import com.swissre.app.dao.Employee;
import java.util.Map;

public interface SalaryAnalysisStrategy {
    void analyze(Map<Integer, Employee> employeeMap);
} 