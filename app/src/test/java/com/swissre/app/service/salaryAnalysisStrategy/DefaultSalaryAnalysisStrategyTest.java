package com.swissre.app.service.salaryAnalysisStrategy;

import com.swissre.app.dao.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DefaultSalaryAnalysisStrategyTest {
    private DefaultSalaryAnalysisStrategy strategy;
    private Map<Integer, Employee> employeeMap;

    @BeforeEach
    void setUp() {
        strategy = new DefaultSalaryAnalysisStrategy();
        employeeMap = new HashMap<>();
        // CEO
        Employee ceo = Employee.builder()
                .id(1)
                .firstName("John")
                .lastName("Smith")
                .salary(100000)
                .managerId(null)
                .subordinates(new HashSet<>())
                .isOverpaid(false)
                .differentialAmount(0)
                .empToCeoDepth(0)
                .isCeo(true)
                .build();
        // Manager
        Employee manager = Employee.builder()
                .id(2)
                .firstName("Jane")
                .lastName("Doe")
                .salary(80000)
                .managerId(1)
                .subordinates(new HashSet<>())
                .isOverpaid(false)
                .differentialAmount(0)
                .empToCeoDepth(1)
                .isCeo(false)
                .build();
        // Subordinate
        Employee subordinate = Employee.builder()
                .id(3)
                .firstName("Bob")
                .lastName("Brown")
                .salary(60000)
                .managerId(1)
                .subordinates(new HashSet<>())
                .isOverpaid(false)
                .differentialAmount(0)
                .empToCeoDepth(1)
                .isCeo(false)
                .build();
        // Assign subordinates
        ceo.getSubordinates().add(manager);
        ceo.getSubordinates().add(subordinate);
        employeeMap.put(1, ceo);
        employeeMap.put(2, manager);
        employeeMap.put(3, subordinate);
    }

    @Test
    void testAnalyze_OverpaidAndUnderpaidLogic() {
        strategy.analyze(employeeMap);
        Employee ceo = employeeMap.get(1);
        // CEO has two subordinates: 80k and 60k, avg=70k, min=84k, max=105k, CEO salary: 100k (should not be overpaid or underpaid)
        assertFalse(ceo.isOverpaid());
        assertEquals(0d, ceo.getDifferentialAmount());
        Employee manager = employeeMap.get(2);
        // Manager has no subordinates, should not be overpaid
        assertFalse(manager.isOverpaid());
        assertEquals(0d, manager.getDifferentialAmount());
        Employee subordinate = employeeMap.get(3);
        assertFalse(subordinate.isOverpaid());
        assertEquals(0d, subordinate.getDifferentialAmount());
    }

    @Test
    void testAnalyze_OverpaidManager() {
        // Make manager overpaid by increasing salary
        Employee manager = employeeMap.get(2);
        manager.setSalary(120000);
        employeeMap.get(1).getSubordinates().clear();
        employeeMap.get(1).getSubordinates().add(manager);
        strategy.analyze(employeeMap);
        // Manager has no subordinates, so should not be overpaid
        assertFalse(manager.isOverpaid());
        // Now add a subordinate to manager
        Employee subordinate = employeeMap.get(3);
        manager.getSubordinates().add(subordinate);
        subordinate.setManagerId(2);
        strategy.analyze(employeeMap);
        // Manager has one subordinate: 60k, avg=60k, min=72k, max=90k, salary=120k (overpaid)
        assertTrue(manager.isOverpaid());
        assertEquals(30000d, manager.getDifferentialAmount());
    }
} 