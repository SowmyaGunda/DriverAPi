package com.technicaltest.driverapi.unit;

import com.technicaltest.driverapi.respositories.DriverDetailsRepositoryImpl;
import com.technicaltest.driverapi.respositories.DriverDto;
import com.technicaltest.driverapi.utils.CSVFileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DriverDetailsRepositoryTest {
    @Mock
    CSVFileUtils csvFileUtils;
    @Mock
    DriverDto driverDto;
    DriverDetailsRepositoryImpl driverDetailsRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        driverDetailsRepository = new DriverDetailsRepositoryImpl(csvFileUtils);
    }

    @Test
    public void whenSaveDetailsIsInvokedThenItShouldCallWriteToCSVFileOnCSVUtils() {
        try {
            driverDetailsRepository.saveDriver(driverDto);
            verify(csvFileUtils, times(1)).writeToCSVFile(anyCollection());
        } catch (Exception exception) {
            Assert.fail(exception.getMessage());
        }
    }

    @Test
    public void whenSaveDetailsIsInvokedAndWriteToCSVFileOnCSVUtilsThorwsExceptionThenItShouldNotSwallowException() {
        try {

            doThrow(new IOException("Some thing gone wrong")).
                    when(csvFileUtils).writeToCSVFile(anyCollection());

            Exception thrown = assertThrows(
                    Exception.class,
                    () -> driverDetailsRepository.saveDriver(driverDto)
            );

            assertTrue(thrown.getMessage().equals("Some thing gone wrong"));

        } catch (Exception exception) {
            Assert.fail(exception.getMessage());
        }
    }

    @Test
    public void whenGetDriversByDateIsInvokedThenItShouldCallGetDriversByDateOnDriverDetailsRepository() {
        try {
            Date today = new Date();
            driverDetailsRepository.getDriversByDate(today);
            verify(csvFileUtils, times(1)).readFromCSVFile();
        } catch (Exception exception) {
            Assert.fail(exception.getMessage());
        }
    }

    @Test()
    public void whenGetDriversByDateIsInvokedAndGetDriversByDateOnDriverDetailsRepositoryThorwsExceptionThenItShouldNotSwallowException() {
        try {
            Date today = new Date();

            when(csvFileUtils.readFromCSVFile()).thenThrow(new IOException("Something gone wrong"));

            Exception thrown = assertThrows(
                    Exception.class,
                    () -> driverDetailsRepository.getDriversByDate(today)
            );

            assertTrue(thrown.getMessage().equals("Something gone wrong"));

        } catch (Exception exception) {
            Assert.fail(exception.getMessage());
        }
    }

    @Test
    public void whenGetDriversIsInvokedThenItShouldCallGetDriversByDateOnDriverDetailsRepository() {
        try {
            driverDetailsRepository.getAllDrivers();
            verify(csvFileUtils, times(1)).readFromCSVFile();
        } catch (Exception exception) {
            Assert.fail(exception.getMessage());
        }
    }

    @Test()
    public void whenGetDriversIsInvokedAndGetDriversByDateOnDriverDetailsRepositoryThorwsExceptionThenItShouldNotSwallowException() {
        try {
            when(csvFileUtils.readFromCSVFile()).thenThrow(new IOException("Something gone wrong"));

            Exception thrown = assertThrows(
                    Exception.class,
                    () -> driverDetailsRepository.getAllDrivers()
            );

            assertTrue(thrown.getMessage().equals("Something gone wrong"));

        } catch (Exception exception) {
            Assert.fail(exception.getMessage());
        }
    }
}
