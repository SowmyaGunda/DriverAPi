package com.technicaltest.driverapi.respositories;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

public interface DriverDetailsRepository {
    public void saveDriver(DriverDto driverDto) throws Exception;
    public Collection<DriverDto> getDriversByDate(Date date) throws Exception;
    public Collection<DriverDto> getAllDrivers() throws Exception;
}
