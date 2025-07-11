package com.swissre.app.service.readFile;

import com.swissre.app.exceptions.FileReadException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import static com.swissre.app.util.Constants.CSV_FILE_READ_ERROR;

public class CSVFileReader implements IFileReader {
    @Override
    public List<String> read(String fileName) {
        try (InputStream input = CSVFileReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new FileNotFoundException(fileName + " doesnt exist at src/main/resources/ directory.");
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
                // Handling ZWNBSP character before returning the csv rows
                return reader.lines().map(l -> l.replace("\uFEFF", "")).collect(Collectors.toList());
            }
        } catch (Exception e) {
            throw new FileReadException(CSV_FILE_READ_ERROR, e);
        }
    }
} 