package com.technicaltest.driverapi.core;

import com.technicaltest.driverapi.respositories.DriverDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Date;

public class DriverDetailsServiceImpl implements DriverDetailsService {

    private final DriverDetailsRepository driverDetailsRepository;
    private final UniqueIdService uniqueIdService;

    @Autowired
    public DriverDetailsServiceImpl(DriverDetailsRepository driverDetailsRepository, UniqueIdService uniqueIdService)
    {
        this.driverDetailsRepository = driverDetailsRepository;
        this.uniqueIdService = uniqueIdService;
    }

    @Override
    public void SaveDetails(Driver driver) {

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
