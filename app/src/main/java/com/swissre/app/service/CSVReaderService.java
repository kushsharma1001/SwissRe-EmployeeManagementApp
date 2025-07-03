package com.swissre.app.service;

import com.swissre.app.EmployeeManagementApplication;
import com.swissre.app.exceptions.EmptyCsvFileException;
import com.swissre.app.exceptions.FileReadException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import static com.swissre.app.util.Constants.CSV_FILE_READ_ERROR;

public class CSVReaderService {

    public List<String> read(String fileName) {
        try (InputStream input = EmployeeManagementApplication.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new FileNotFoundException(fileName + " doesnt exist at src/main/resources/ directory.");
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
                return reader.lines().collect(Collectors.toList());
            }
        } catch (Exception e) {
            throw new FileReadException(CSV_FILE_READ_ERROR, e);
        }
    }

    public void validateCsvAndThrowError(List<String> dataFromCsv){
        if(dataFromCsv == null || dataFromCsv.isEmpty() || dataFromCsv.size() == 1){
            throw new EmptyCsvFileException("Input csv file is empty.");
        }
    }
}
