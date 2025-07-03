package com.swissre.app.util;

public class Constants {

    private Constants() throws IllegalAccessException {
        throw new IllegalAccessException("Not allowed to instantiate this class.");
    }
    /**
     * This variable is used to read employee data from 1st row of csv file as 0th row in the csv file is for header.
     */
    public static final int EMPLOYEE_DATA_ROW_NUM_START_INDEX = 1;
    public static final String CSV_DATA_FILE_NAME = "employees.csv";
    public static final String CSV_FILE_READ_ERROR = "Error reading file from src/main/resources directory with name: \"" + CSV_DATA_FILE_NAME + "\". Make sure that file exists in this location with the same name.";
}
