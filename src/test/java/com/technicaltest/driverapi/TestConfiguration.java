package com.technicaltest.driverapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicaltest.driverapi.core.Driver;
import com.technicaltest.driverapi.core.DriverDetailsService;
import com.technicaltest.driverapi.core.DriverDetailsServiceImpl;
import com.technicaltest.driverapi.core.UniqueIdService;
import com.technicaltest.driverapi.respositories.DriverDetailsRepository;
import com.technicaltest.driverapi.respositories.DriverDetailsRepositoryImpl;
import com.technicaltest.driverapi.respositories.DriverDto;
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
            public Collection<DriverDto> getAllDrivers() {
                ArrayList<DriverDto> drivers = new ArrayList<DriverDto>();
                drivers.add(new DriverDto("unique-id-1","Driver 1","abc","09/04/1991", "23/08/2020"));
                drivers.add(new DriverDto("unique-id-2","Driver 2","xyz","13/04/1987","24/08/2020"));
                return drivers;
            }
        };
    }

}
