package com.swissre.app.util;

public class Constants {

    private Constants() throws IllegalAccessException {
        throw new IllegalAccessException("Not allowed to instantiate this class.");
    }
    /**
     * This variable is used to read employee data from 1st row of csv file as 0th row in the csv file is for header.
     */
    public static final int EMPLOYEE_DATA_ROW_NUM_START_INDEX = 1;

    public static final String IS_UNDERPAID_BY = " is underpaid by: ";

    public static final String IS_OVERPAID_BY = " is overpaid by: ";

    public static final String HAS_A_REPORTING_DEPTH_OF_TEXT = " has a reporting depth of ";
    public static final String CSV_FILE_READ_ERROR = "Error reading file at src/main/resources/ directory with name: \"employees.csv\". Make sure that file exists in this location with the same name.";
    public static final String CSV_DATA_FILE_NAME = "employees.csv";
}
