package com.technicaltest.driverapi.core;

import java.util.Collection;
import java.util.Date;

public interface DriverDetailsService {
    public void SaveDetails(Driver driver);

    Collection<Driver> getDrivers(Date date);
}
