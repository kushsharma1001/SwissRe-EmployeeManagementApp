package com.swissre.app.service.employee;

import com.swissre.app.dao.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    private EmployeeService service;
    private Map<Integer, Employee> employeeMap;
    private List<String> employeeRecords;

    @BeforeEach
    void setUp() {
        service = new EmployeeService();
        employeeMap = new HashMap<>();
        employeeRecords = Arrays.asList(
                "1,John,Smith,100000,",
                "2,Jane,Doe,80000,1",
                "3,Bob,Brown,60000,1",
                "4,Alice,White,30000,2",
                "5,Ron,Dohn,30000,4",
                "6,Rony,Toss,80000,5",
                "7,Rony,Toss,80000,6",
                "8,Jacob,Mary,30000,7"
        );
    }

    @Test
    void testAddEmployeesFromCsvToMap() {
        service.addEmployeesFromCsvToMap(employeeRecords, employeeMap);
        assertEquals(8, employeeMap.size());
        Employee ceo = employeeMap.get(1);
        assertEquals("John", ceo.getFirstName());
        assertNull(ceo.getManagerId());
        Employee jane = employeeMap.get(2);
        assertEquals("Jane", jane.getFirstName());
        assertEquals(1, jane.getManagerId());
    }

    @Test
    void testAssignEmployeeSubordinates() {
        service.addEmployeesFromCsvToMap(employeeRecords, employeeMap);
        service.assignEmployeeSubordinates(employeeMap);
        Employee ceo = employeeMap.get(1);
        Set<Employee> ceoSubs = ceo.getSubordinates();
        assertEquals(2, ceoSubs.size());
        Set<Integer> subIds = new HashSet<>();
        for (Employee e : ceoSubs) subIds.add(e.getId());
        assertTrue(subIds.contains(2));
        assertTrue(subIds.contains(3));
        Employee jane = employeeMap.get(2);
        assertEquals(1, jane.getSubordinates().size());
        assertEquals(4, jane.getSubordinates().iterator().next().getId());
        Employee bob = employeeMap.get(3);
        assertEquals(0, bob.getSubordinates().size());
    }

    @Test
    void testAnalyzeUnderPaidOrOverPaidEmployees() {
        service.addEmployeesFromCsvToMap(employeeRecords, employeeMap);
        service.assignEmployeeSubordinates(employeeMap);
        service.analyzeUnderPaidOrOverPaidEmployees(employeeMap);

        Employee ceo = employeeMap.get(1);
        // CEO has two subordinates: 80k and 60k, avg=70k, min=84k, max=105k, CEO salary: 1Lac
        assertFalse(ceo.isOverpaid());
        assertEquals(0d, ceo.getDifferentialAmount());
        Employee jane = employeeMap.get(2);
        // Jane has one subordinate: 30k, avg=30k, min=36k, max=45k, salary=80k (overpaid)
        assertTrue(jane.isOverpaid());
        assertEquals(35000d, jane.getDifferentialAmount());
        Employee bob = employeeMap.get(3);
        // Bob has no subordinates, should not be overpaid
        assertFalse(bob.isOverpaid());
        assertEquals(0d, bob.getDifferentialAmount());
    }

    @Test
    void testAssignEmployeeToCeoDepth() {
        service.addEmployeesFromCsvToMap(employeeRecords, employeeMap);
        service.assignEmployeeSubordinates(employeeMap);
        service.assignEmployeeToCeoDepth(employeeMap);
        assertEquals(0, employeeMap.get(1).getEmpToCeoDepth());
        assertEquals(0, employeeMap.get(2).getEmpToCeoDepth());
        assertEquals(0, employeeMap.get(3).getEmpToCeoDepth());
        assertEquals(1, employeeMap.get(4).getEmpToCeoDepth());
        assertEquals(2, employeeMap.get(5).getEmpToCeoDepth());
        assertEquals(3, employeeMap.get(6).getEmpToCeoDepth());
        assertEquals(4, employeeMap.get(7).getEmpToCeoDepth());
        assertEquals(5, employeeMap.get(8).getEmpToCeoDepth());
    }
} 