package com.technicaltest.driverapi;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class CSVFileUtils {
    private final static String fileName = "DriverDetails.csv";
    private final static char COMMA_SEPARATOR = ',';

    public void writeToCSVFile(String[] csvRowData) throws IOException {
        try {
            // create FileWriter object with file as parameter
            FileWriter outputFile = new FileWriter(fileName, true);

            // create CSVWriter with ',' as separator
            CSVWriter writer = new CSVWriter(outputFile, COMMA_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            writer.writeNext(csvRowData);

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }


    public Collection<String[]> readFromCSVFile() throws IOException {
        try {
            // Create an object of file reader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(fileName);

            // create csvReader object and skip first Line
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .build();
            return csvReader.readAll();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
