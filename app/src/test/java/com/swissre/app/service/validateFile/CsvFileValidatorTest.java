package com.swissre.app.service.validateFile;

import com.swissre.app.exceptions.EmptyCsvFileException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvFileValidatorTest {
    private final CsvFileValidator validator = new CsvFileValidator();

    @Test
    void testValidateCsvAndThrowError_NullList_ThrowsException() {
        assertThrows(EmptyCsvFileException.class, () -> validator.validateCsvAndThrowError(null));
    }

    @Test
    void testValidateCsvAndThrowError_EmptyList_ThrowsException() {
        assertThrows(EmptyCsvFileException.class, () -> validator.validateCsvAndThrowError(Collections.emptyList()));
    }

    @Test
    void testValidateCsvAndThrowError_HeaderOnly_ThrowsException() {
        List<String> headerOnly = Collections.singletonList("Id,firstName,lastName,salary,managerId");
        assertThrows(EmptyCsvFileException.class, () -> validator.validateCsvAndThrowError(headerOnly));
    }

    @Test
    void testValidateCsvAndThrowError_ValidData_DoesNotThrow() {
        List<String> validData = Arrays.asList(
                "Id,firstName,lastName,salary,managerId",
                "123,Joe,Doe,60000,",
                "124,Martin,Chekov,45000,123"
        );
        assertDoesNotThrow(() -> validator.validateCsvAndThrowError(validData));
    }
} 