package com.swissre.app.service.salaryAnalysisStrategy;

import com.swissre.app.dao.Employee;

import java.util.Map;

public class DefaultSalaryAnalysisStrategy implements SalaryAnalysisStrategy {
    @Override
    public void analyze(Map<Integer, Employee> employeeMap) {
        for (Employee emp : employeeMap.values()) {
            if (!emp.getSubordinates().isEmpty()) {
                double avg = emp.getSubordinates().parallelStream().mapToDouble(Employee::getSalary).average().orElse(0);
                double min = avg + (avg * 0.2);
                double max = avg + (avg * 0.5);

                if (emp.getSalary() < min) {
                    emp.setDifferentialAmount(min - emp.getSalary());
                } else if (emp.getSalary() > max) {
                    emp.setOverpaid(true);
                    emp.setDifferentialAmount(emp.getSalary() - max);
                }
            }
        }
    }
} 