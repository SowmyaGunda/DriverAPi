package com.technicaltest.driverapi.respositories;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import com.opencsv.CSVWriter;

public class DriverDetailsRepositoryImpl implements  DriverDetailsRepository{
    private final String fileName = "DriverDetails.csv";
    private final char COMMA_SEPARATOR = ',';
    @Override
    public void saveDriver(DriverDto driverDto) throws IOException {

        try {
            // create FileWriter object with file as parameter
            FileWriter outputFile = new FileWriter(fileName,true);

            // create CSVWriter with ',' as separator
            CSVWriter writer = new CSVWriter(outputFile, COMMA_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            String[] rowData = {driverDto.uniqueId, driverDto.firstName, driverDto.lastName, driverDto.dob, driverDto.creationDate};

            // create a List which contains Data
            List<String[]> data = new ArrayList<String[]>();
            data.add(rowData);

            writer.writeNext(rowData);

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public Collection<DriverDto> getDriversByDate(Date date) {
        List<DriverDto> driversData = new ArrayList<>();
        try {
            // Create an object of file reader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(fileName);

            // create csvReader object and skip first Line
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .build();
            List<String[]> allData = csvReader.readAll();

            for (String[] row : allData) {
                Date creationDate = DriverDto.formatter.parse(row[4]);
                if(creationDate.after(date)) {
                    driversData.add(new DriverDto(row[0], row[1], row[2], row[3], row[4]));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return driversData;    }

    @Override
    public Collection<DriverDto> getAllDrivers() {
        List<DriverDto> driversData = new ArrayList<>();
        try {
            // Create an object of file reader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(fileName);

            // create csvReader object and skip first Line
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .build();
            List<String[]> allData = csvReader.readAll();

            for (String[] row : allData) {
                driversData.add(new DriverDto(row[0],row[1],row[2],row[3],row[4]));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return driversData;
    }
}
