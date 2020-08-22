package com.technicaltest.driverapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicaltest.driverapi.core.Driver;
import com.technicaltest.driverapi.core.DriverDetailsService;
import com.technicaltest.driverapi.core.DriverDetailsServiceImpl;
import com.technicaltest.driverapi.core.UniqueIdService;
import com.technicaltest.driverapi.respositories.DriverDetailsRepository;
import com.technicaltest.driverapi.respositories.DriverDetailsRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@ComponentScan("com.technicaltest.driverapi")
public class TestConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean DriverDetailsRepository driverDetailsRepository()
    {
        return new DriverDetailsRepositoryImpl(){
            @Override
            public Collection<Driver> GetAllDrivers() {
                ArrayList<Driver> drivers = new ArrayList<Driver>();
                drivers.add(new Driver("Driver 1"));
                drivers.add(new Driver("Driver 2"));
                return drivers;
            }
        };
    }

}
