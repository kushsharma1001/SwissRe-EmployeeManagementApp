package com.swissre.app.service.employee;

import com.swissre.app.dao.Employee;
import com.swissre.app.service.salaryAnalysisStrategy.DefaultSalaryAnalysisStrategy;
import com.swissre.app.service.salaryAnalysisStrategy.SalaryAnalysisStrategy;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class EmployeeService {
    private static final Logger logger = Logger.getLogger(EmployeeService.class.getName());
    private SalaryAnalysisStrategy salaryAnalysisStrategy = new DefaultSalaryAnalysisStrategy();

    public void addEmployeesFromCsvToMap(List<String> employeeRecords, Map<Integer, Employee> employeeMap){
        for (String empRecord : employeeRecords) {
            String[] employeeInfo = empRecord.split(",");
            // Create employee from csv
            int id = Integer.parseInt(employeeInfo[0]);
            String firstName = employeeInfo[1];
            String lastName = employeeInfo[2];
            double salary = Double.parseDouble(employeeInfo[3]);
            Integer managerId = employeeInfo.length > 4 && !employeeInfo[4].isEmpty() ? Integer.parseInt(employeeInfo[4]) : null;
            Employee emp = Employee.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .salary(salary)
                .managerId(managerId)
                .subordinates(new HashSet<>())
                .isOverpaid(false)
                .differentialAmount(0)
                .empToCeoDepth(0)
                .isCeo(managerId == null)
                .build();
            employeeMap.put(id, emp);
            //Update the subordinate list of the manager (if manager exists already as an employee) as new employee has been assigned.
            if(employeeMap.containsKey(managerId)){
                employeeMap.get(managerId).getSubordinates().add(emp);
            }
        }
    }

    public void assignEmployeeSubordinates(Map<Integer, Employee> employeeMap){
        for (Employee emp : employeeMap.values()) {
            Integer managerId = emp.getManagerId();
            if (managerId != null) {
                Employee manager = employeeMap.get(managerId);
                Set<Employee> subOrdinates = manager.getSubordinates();
                subOrdinates.add(emp);
            }
        }
    }

    public void setSalaryAnalysisStrategy(SalaryAnalysisStrategy strategy) {
        this.salaryAnalysisStrategy = strategy;
    }

    public void analyzeUnderPaidOrOverPaidEmployees(Map<Integer, Employee> employeeMap) {
        salaryAnalysisStrategy.analyze(employeeMap);
    }

    public void assignEmployeeToCeoDepth(Map<Integer, Employee> employeeMap) {
        for (Employee emp : employeeMap.values()) {
            int depth = getDepth(emp, employeeMap);
            emp.setEmpToCeoDepth(depth);
        }
    }

    public void display(Map<Integer, Employee> employeeMap){
        logger.log(Level.INFO,"-- Underpaid Managers --\n");
        logger.log(Level.INFO, employeeMap.values().parallelStream().filter(emp -> !emp.isOverpaid() && emp.getDifferentialAmount() !=0).collect(Collectors.toList()).toString());
        logger.log(Level.INFO,"\n-- Overpaid Managers --");
        logger.log(Level.INFO, employeeMap.values().parallelStream().filter(emp -> emp.isOverpaid() && emp.getDifferentialAmount() !=0).collect(Collectors.toList()).toString());
        logger.log(Level.INFO,"\n-- Employees with Deep Reporting Lines --");
        logger.log(Level.INFO, employeeMap.values().parallelStream().filter(emp -> emp.getEmpToCeoDepth() > 4).collect(Collectors.toList()).toString());
    }

    private int getDepth(Employee emp, Map<Integer, Employee> map) {
        int depth = 0;
        while (emp.getManagerId() != null) {
            Integer managerId = emp.getManagerId();
            emp = map.get(managerId);
            if(emp.isCeo()){
                return depth;
            }
            depth++;
        }
        return depth;
    }
}
