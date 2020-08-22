package com.technicaltest.driverapi;

import com.technicaltest.driverapi.core.DriverDetailsService;
import com.technicaltest.driverapi.core.DriverDetailsServiceImpl;
import com.technicaltest.driverapi.core.UniqueIdService;
import com.technicaltest.driverapi.respositories.DriverDetailsRepository;
import com.technicaltest.driverapi.respositories.DriverDetailsRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.technicaltest.driverapi")
public class ApplicationConfiguration {

    @Bean
    public DriverDetailsService driverDetailsService(DriverDetailsRepository driverDetailsRepository){
        return new DriverDetailsServiceImpl(driverDetailsRepository, new UniqueIdService());
    }

    @Bean DriverDetailsRepository driverDetailsRepository()
    {
        return new DriverDetailsRepositoryImpl();
    }
}
