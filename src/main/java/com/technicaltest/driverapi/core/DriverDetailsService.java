package com.technicaltest.driverapi.core;

import com.technicaltest.driverapi.respositories.DriverDto;

import java.util.Collection;
import java.util.Date;

public interface DriverDetailsService {
    public void saveDetails(Driver driver) throws Exception;

    Collection<DriverDto> getDriversByDate(Date date);

    Collection<DriverDto> getAllDrivers();
}
