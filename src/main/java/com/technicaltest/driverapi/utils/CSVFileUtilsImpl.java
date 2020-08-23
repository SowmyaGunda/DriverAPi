package com.technicaltest.driverapi.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class CSVFileUtilsImpl implements CSVFileUtils {
    private final static String fileName = "DriverDetails.csv";
    private final static char COMMA_SEPARATOR = ',';

    @Override
    public void writeToCSVFile(Collection<String> csvRowData) throws IOException {
        try {
            // create FileWriter object with file as parameter
            FileWriter outputFile = new FileWriter(fileName, true);

            // create CSVWriter with ',' as separator
            CSVWriter writer = new CSVWriter(outputFile, COMMA_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            writer.writeNext((String[]) csvRowData.toArray());

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Collection<String[]> readFromCSVFile() throws IOException {
        try {
            // Create an object of file reader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(fileName);

            // create csvReader object and skip first Line
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .build();
            return csvReader.readAll();

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
