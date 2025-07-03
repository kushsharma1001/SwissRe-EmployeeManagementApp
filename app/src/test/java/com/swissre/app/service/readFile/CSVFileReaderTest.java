package com.swissre.app.service.readFile;

import com.swissre.app.exceptions.FileReadException;
import com.swissre.app.util.Constants;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVFileReaderTest {
    private final CSVFileReader csvFileReader = new CSVFileReader();

    @Test
    void testRead_ValidFile_ReturnsLines() {
        List<String> lines = csvFileReader.read(Constants.CSV_DATA_FILE_NAME);
        assertNotNull(lines);
        assertFalse(lines.isEmpty());
        assertEquals("Id,firstName,lastName,salary,managerId", lines.get(0));
        assertEquals(6, lines.size()); // 1 header + 5 data rows
    }

    @Test
    void testRead_FileNotFound_ThrowsFileReadException() {
        String missingFile = "nonexistent.csv";
        FileReadException ex = assertThrows(FileReadException.class, () -> csvFileReader.read(missingFile));
        assertTrue(ex.getMessage().contains(Constants.CSV_FILE_READ_ERROR));
        assertNotNull(ex.getCause());
        assertTrue(ex.getCause() instanceof java.io.FileNotFoundException);
    }
} 