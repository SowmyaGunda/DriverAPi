package com.technicaltest.driverapi.core;

import com.technicaltest.driverapi.respositories.DriverDetailsRepository;
import com.technicaltest.driverapi.respositories.DriverDto;
import com.technicaltest.driverapi.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class DriverDetailsServiceImpl implements DriverDetailsService {

    private final DriverDetailsRepository driverDetailsRepository;
    private final UniqueIdService uniqueIdService;
    public final static SimpleDateFormat FORMATTER = new SimpleDateFormat(Constants.DateFormat);

    @Autowired
    public DriverDetailsServiceImpl(DriverDetailsRepository driverDetailsRepository,
                                    UniqueIdService uniqueIdService) {
        this.driverDetailsRepository = driverDetailsRepository;
        this.uniqueIdService = uniqueIdService;
    }

    @Override
    public void saveDetails(Driver driver) throws Exception {
        this.driverDetailsRepository.saveDriver(
                new DriverDto(uniqueIdService.getNewId(), driver.firstname, driver.lastname,
                        driver.date_of_birth, FORMATTER.format(new Date())));
    }

    @Override
    public Collection<DriverDto> getAllDrivers() throws Exception {
        return this.driverDetailsRepository.getAllDrivers();
    }

    @Override
    public Collection<DriverDto> getDriversByDate(Date date) throws Exception {
        return this.driverDetailsRepository.getDriversByDate(date);
    }
}
