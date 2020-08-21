package com.technicaltest.driverapi;

import com.technicaltest.driverapi.core.DriverDetailsService;
import com.technicaltest.driverapi.core.DriverDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.technicaltest.driverapi")
public class ApplicationConfiguration {

    @Bean
    public DriverDetailsService driverDetailsService(){
        //TODO: Add dependency
        return new DriverDetailsServiceImpl(null);
    }
}
