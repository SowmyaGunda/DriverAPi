package com.technicaltest.driverapi.respositories;

import com.technicaltest.driverapi.core.Driver;

import java.util.Collection;
import java.util.Date;

public interface DriverDetailsRepository {
    public void SaveDriver(DriverDto driverDto);
    public Collection<Driver> GetDriversByDate(Date date);
    public Collection<Driver> GetAllDrivers();
}
