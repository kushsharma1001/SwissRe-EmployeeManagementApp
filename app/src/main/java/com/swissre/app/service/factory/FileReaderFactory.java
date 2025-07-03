package com.swissre.app.service.factory;

import com.swissre.app.service.readFile.CSVFileReader;
import com.swissre.app.service.readFile.IFileReader;

public class FileReaderFactory {
    private FileReaderFactory(){
    }
    
    public static IFileReader getFileReader(String fileType) {
        if ("csv".equalsIgnoreCase(fileType)) {
            return new CSVFileReader();
        }
        throw new UnsupportedOperationException("File type not supported: " + fileType);
    }
} 