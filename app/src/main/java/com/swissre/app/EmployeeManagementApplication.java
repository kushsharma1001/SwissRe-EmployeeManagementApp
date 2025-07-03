package com.swissre.app;

import com.swissre.app.dao.Employee;
import com.swissre.app.service.employee.EmployeeService;
import com.swissre.app.service.factory.FileReaderFactory;
import com.swissre.app.service.readFile.IFileReader;
import com.swissre.app.service.validateFile.CsvFileValidator;
import com.swissre.app.service.validateFile.IFileValidator;

import java.util.*;
import static com.swissre.app.util.Constants.*;

public class EmployeeManagementApplication {
	private static final IFileReader csvReaderService = FileReaderFactory.getFileReader("csv");
	private static final IFileValidator csvFileValidator = new CsvFileValidator();
	private static final EmployeeService employeeService = new EmployeeService();

	public static void main(String[] args) {
		Map<Integer, Employee> employeeMap = new HashMap<>();

		List<String> dataFromCsvWithHeader = csvReaderService.read(CSV_DATA_FILE_NAME);
		csvFileValidator.validateCsvAndThrowError(dataFromCsvWithHeader);
		List<String> dataFromCsvWithoutHeader = dataFromCsvWithHeader.subList(EMPLOYEE_DATA_ROW_NUM_START_INDEX, dataFromCsvWithHeader.size()); // Skip header at index 0

		employeeService.addEmployeesFromCsvToMap(dataFromCsvWithoutHeader, employeeMap);

		// Assign subordinates to all employees
		employeeService.assignEmployeeSubordinates(employeeMap);

		//Identify overpaid or underpaid employees
		employeeService.analyzeUnderPaidOrOverPaidEmployees(employeeMap);

		// Assign long reporting hierarchy depth
		employeeService.assignEmployeeToCeoDepth(employeeMap);

		// Display results
		employeeService.display(employeeMap);
	}
}
