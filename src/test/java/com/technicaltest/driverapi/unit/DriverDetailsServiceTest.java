package com.technicaltest.driverapi.unit;

import com.technicaltest.driverapi.core.Driver;
import com.technicaltest.driverapi.core.DriverDetailsService;
import com.technicaltest.driverapi.core.DriverDetailsServiceImpl;
import com.technicaltest.driverapi.core.UniqueIdService;
import com.technicaltest.driverapi.respositories.DriverDetailsRepository;
import com.technicaltest.driverapi.respositories.DriverDto;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DriverDetailsServiceTest {

    @Mock
    DriverDetailsRepository driverDetailsRepository;
    @Mock
    UniqueIdService uniqueIdService;

    Driver driver;

    DriverDetailsService driverDetailsService;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        driver = new Driver("first name", "last name", "1990-10-20");
        driverDetailsService = new DriverDetailsServiceImpl(driverDetailsRepository, uniqueIdService);
    }

    @Test
    public void whenSaveDetailsIsInvokedThenItShouldCallSaveDriverOnDriverDetailsRepository() {
        try {
            driverDetailsService.saveDetails(driver);
            verify(driverDetailsRepository, times(1)).saveDriver(any(DriverDto.class));
        } catch (Exception exception) {
            Assert.fail(exception.getMessage());
        }
    }

    @Test()
    public void whenSaveDetailsIsInvokedAndSaveDriverOnDriverDetailsRepositoryThorwsExceptionThenItShouldNotSwallowException() {
        try {

            doThrow(new Exception("Some thing gone wrong")).
                    when(driverDetailsRepository).saveDriver(any(DriverDto.class));

            Exception thrown = assertThrows(
                    Exception.class,
                    () -> driverDetailsService.saveDetails(driver)
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
            driverDetailsService.getDriversByDate(today);
            verify(driverDetailsRepository, times(1)).getDriversByDate(today);
        } catch (Exception exception) {
            Assert.fail(exception.getMessage());
        }
    }

    @Test()
    public void whenGetDriversByDateIsInvokedAndGetDriversByDateOnDriverDetailsRepositoryThorwsExceptionThenItShouldNotSwallowException() {
        try {
            Date today = new Date();

            doThrow(new Exception("Some thing gone wrong")).
                    when(driverDetailsRepository).getDriversByDate(any(Date.class));

            Exception thrown = assertThrows(
                    Exception.class,
                    () -> driverDetailsService.getDriversByDate(today)
            );

            assertTrue(thrown.getMessage().equals("Some thing gone wrong"));

        } catch (Exception exception) {
            Assert.fail(exception.getMessage());
        }
    }

    @Test
    public void whenGetDriversIsInvokedThenItShouldCallGetDriversByDateOnDriverDetailsRepository() {
        try {
            driverDetailsService.getAllDrivers();
            verify(driverDetailsRepository, times(1)).getAllDrivers();
        } catch (Exception exception) {
            Assert.fail(exception.getMessage());
        }
    }

    @Test()
    public void whenGetDriversIsInvokedAndGetDriverseOnDriverDetailsRepositoryThorwsExceptionThenItShouldNotSwallowException() {
        try {

            doThrow(new Exception("Some thing gone wrong")).
                    when(driverDetailsRepository).getAllDrivers();

            Exception thrown = assertThrows(
                    Exception.class,
                    () -> driverDetailsService.getAllDrivers()
            );

            assertTrue(thrown.getMessage().equals("Some thing gone wrong"));

        } catch (Exception exception) {
            Assert.fail(exception.getMessage());
        }
    }
}