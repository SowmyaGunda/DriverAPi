package com.technicaltest.driverapi.respositories;

import com.technicaltest.driverapi.core.Driver;

import java.util.Collection;
import java.util.Date;

public class DriverDetailsRepositoryImpl implements  DriverDetailsRepository{
    @Override
    public void SaveDriver(DriverDto driverDto) {

    }

    @Override
    public Collection<Driver> GetDriversByDate(Date date) {
        return null;
    }

    @Override
    public Collection<Driver> GetAllDrivers() {
        return null;
    }
}
