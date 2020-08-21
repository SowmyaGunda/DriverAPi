package com.technicaltest.driverapi.core;

import java.util.Collection;
import java.util.Date;

public interface DriverDetailsService {
    public void SaveDetails();

    Collection<Driver> getDrivers(Date date);
}
