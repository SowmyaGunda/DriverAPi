package com.technicaltest.driverapi.core;

import com.technicaltest.driverapi.respositories.DriverDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class DriverDetailsServiceImpl implements DriverDetailsService {

    private final DriverDetailsRepository driverDetailsRepository;

    @Autowired
    public DriverDetailsServiceImpl(DriverDetailsRepository driverDetailsRepository)
    {
        this.driverDetailsRepository = driverDetailsRepository;
    }

    @Override
    public void SaveDetails() {

    }

    @Override
    public Collection<Driver> getDrivers(Date date) {
        if(date != null)
        {
            return this.driverDetailsRepository.GetDriversByDate(date);
        }
        else {
            return this.driverDetailsRepository.GetAllDrivers();
        }
    }
}
