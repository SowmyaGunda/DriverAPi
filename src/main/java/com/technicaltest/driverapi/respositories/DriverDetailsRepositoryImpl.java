package com.technicaltest.driverapi.respositories;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import com.opencsv.CSVWriter;

public class DriverDetailsRepositoryImpl implements  DriverDetailsRepository{
    private String fileName = "DriverDetails";
    @Override
    public void saveDriver(DriverDto driverDto) {
        File file = new File(fileName);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter with ',' as separator
            CSVWriter writer = new CSVWriter(outputfile, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            String[] rowdata = {driverDto.uniqueId, driverDto.firstName, driverDto.lastName, driverDto.dob, driverDto.creationDate};

            // create a List which contains Data
            List<String[]> data = new ArrayList<String[]>();
            data.add(rowdata);

            writer.writeAll(data);

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
                    .withSkipLines(1)
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
                    .withSkipLines(1)
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
