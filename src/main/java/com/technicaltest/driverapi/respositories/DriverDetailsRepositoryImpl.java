package com.technicaltest.driverapi.respositories;

import com.technicaltest.driverapi.utils.CSVFileUtils;
import com.technicaltest.driverapi.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DriverDetailsRepositoryImpl implements DriverDetailsRepository {

    private final CSVFileUtils csvFileUtils;

    @Autowired
    public DriverDetailsRepositoryImpl(CSVFileUtils csvFileUtils) {

        this.csvFileUtils = csvFileUtils;
    }

    @Override
    public void saveDriver(DriverDto driverDto) throws Exception {
        try {
            String[] rowData = {driverDto.uniqueId, driverDto.firstname, driverDto.lastname, driverDto.date_of_birth, driverDto.creationDate};
            csvFileUtils.writeToCSVFile(Arrays.asList(rowData));
        } catch (IOException ioException) {
            throw new Exception(ioException.getMessage(), ioException);
        }
    }


    @Override
    public Collection<DriverDto> getDriversByDate(Date date) throws Exception {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DateFormat);
            List<DriverDto> driversData = new ArrayList<>();
            List<String[]> allDriversData = (List<String[]>) csvFileUtils.readFromCSVFile();
            for (String[] row : allDriversData) {
                Date creationDate = dateFormat.parse(row[4]);
                if (creationDate.after(date)) {
                    driversData.add(new DriverDto(row[0], row[1], row[2], row[3], row[4]));
                }
            }
            return driversData;
        } catch (IOException ioException) {
            throw new Exception(ioException.getMessage(), ioException);
        }
    }

    @Override
    public Collection<DriverDto> getAllDrivers() throws Exception {
        try{
            List<DriverDto> driversData = new ArrayList<>();
            List<String[]> allDriversData = (List<String[]>) csvFileUtils.readFromCSVFile();

            for (String[] row : allDriversData) {
                driversData.add(new DriverDto(row[0], row[1], row[2], row[3], row[4]));
            }
            return driversData;
        }
        catch (IOException ioException)
        {
            throw new Exception(ioException.getMessage(), ioException);
        }

    }
}
