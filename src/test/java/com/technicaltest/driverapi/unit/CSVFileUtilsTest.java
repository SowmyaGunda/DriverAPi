package com.technicaltest.driverapi.unit;

import com.technicaltest.driverapi.utils.CSVFileUtils;
import com.technicaltest.driverapi.utils.CSVFileUtilsImpl;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CSVFileUtilsTest {

    CSVFileUtils csvFileUtils;
    final static String fileName = CSVFileUtilsImpl.fileName;

    @BeforeEach
    public void setUp() {
        csvFileUtils = new CSVFileUtilsImpl();
        deleteFile();
    }

    @AfterEach
    public void tearDown(){
        deleteFile();
    }

    @Test
    public void givenAnExistingCsvFileWhenReadFromCSVFileIsCalledThenItShouldReadDataSuccessfully() {

        try {
            //Setup
            setupSampleData();

            String[] sampleData = new String[]{"id", "firstName", "LastName", "1998-09-07", "2020-08-24"};
            List<String[]> allDriversData = (List<String[]>) csvFileUtils.readFromCSVFile();
            for (String[] row : allDriversData) {
                String id = row[0];
                if (id.equals("id")) {
                    assertTrue(Arrays.equals(row, sampleData));
                }
            }

        } catch (IOException ioException) {
            Assert.fail(ioException.getMessage());
        }
    }

    @Test
    public void whenWriteToCSVFileIsCalledThenItShouldWriteDataSuccessfully() {

        try {

            String[] sampleData = new String[]{"id", "firstName", "LastName", "1998-09-07", "2020-08-24"};
            csvFileUtils.writeToCSVFile(Arrays.asList(sampleData));

            //Assertion
            List<String> allLines = readFileData();

            assertTrue(allLines.get(0).equals("id,firstName,LastName,1998-09-07,2020-08-24"));


        } catch (IOException ioException) {
            Assert.fail(ioException.getMessage());
        }
    }


    @Test
    public void readDataWhenFileNotExistsItMustReturnEmptyData() throws IOException {

        assertTrue(csvFileUtils.readFromCSVFile().isEmpty());
    }

    private void deleteFile() {
        if (Files.exists(Paths.get(fileName))) {
            File file = new File(fileName);
            file.delete();
        }
    }

    private void setupSampleData() {

        try {
            FileWriter outputFile = new FileWriter(fileName, true);
            outputFile.write("id,firstName,LastName,1998-09-07,2020-08-24");
            outputFile.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private List<String> readFileData() {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(fileName));
            return allLines;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }
}
