package com.swissre.app.service.validateFile;

import com.swissre.app.exceptions.EmptyCsvFileException;

import java.util.List;

public class CsvFileValidator implements IFileValidator {
    public void validateCsvAndThrowError(List<String> dataFromCsv){
        if(dataFromCsv == null || dataFromCsv.isEmpty() || dataFromCsv.size() == 1){
            throw new EmptyCsvFileException("Input csv file is empty.");
        }
    }
}
