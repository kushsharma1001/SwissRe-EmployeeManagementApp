package com.swissre.app.service.validateFile;

import java.util.List;

public interface IFileValidator {
    public void validateCsvAndThrowError(List<String> dataFromCsv);
}
