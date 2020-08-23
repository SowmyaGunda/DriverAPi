package com.technicaltest.driverapi.respositories;

import com.technicaltest.driverapi.core.Driver;

import java.util.Collection;
import java.util.Date;

public interface DriverDetailsRepository {
    public void saveDriver(DriverDto driverDto);
    public Collection<DriverDto> getDriversByDate(Date date);
    public Collection<DriverDto> getAllDrivers();
}
