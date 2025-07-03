package com.swissre.app.service;

import com.swissre.app.EmployeeManagementApplication;
import com.swissre.app.exceptions.EmptyCsvFileException;
import com.swissre.app.exceptions.FileReadException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static com.swissre.app.util.Constants.CSV_FILE_READ_ERROR;

public class CSVReaderService {

    public List<String> read(String fileName) {
        try {
            Path filePath = Paths.get(EmployeeManagementApplication.class.getClassLoader().getResource(fileName).toURI());
            if (!Files.exists(filePath)) {
               throw new FileNotFoundException(fileName + " doesnt exist at src/main/resources/ directory.");
            }
            return Files.readAllLines(filePath);
        }
        catch (Exception e){
            throw new FileReadException(CSV_FILE_READ_ERROR, e);
        }
    }

    public void validateCsvAndThrowError(List<String> dataFromCsv){
        if(dataFromCsv == null || dataFromCsv.isEmpty() || dataFromCsv.size() == 1){
            throw new EmptyCsvFileException("Input csv file is empty.");
        }
    }
}
