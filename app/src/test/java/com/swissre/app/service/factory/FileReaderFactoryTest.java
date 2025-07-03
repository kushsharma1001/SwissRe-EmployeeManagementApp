package com.swissre.app.service.factory;

import com.swissre.app.service.readFile.CSVFileReader;
import com.swissre.app.service.readFile.IFileReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderFactoryTest {
    @Test
    void testGetFileReader_CsvType_ReturnsCSVFileReader() {
        IFileReader reader = FileReaderFactory.getFileReader("csv");
        assertNotNull(reader);
        assertTrue(reader instanceof CSVFileReader);
    }

    @Test
    void testGetFileReader_UnsupportedType_ThrowsException() {
        UnsupportedOperationException ex = assertThrows(UnsupportedOperationException.class, () ->
                FileReaderFactory.getFileReader("xml"));
        assertTrue(ex.getMessage().contains("File type not supported"));
    }
} 