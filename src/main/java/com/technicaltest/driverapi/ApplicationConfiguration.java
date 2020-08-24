package com.technicaltest.driverapi;

import com.technicaltest.driverapi.core.DriverDetailsService;
import com.technicaltest.driverapi.core.DriverDetailsServiceImpl;
import com.technicaltest.driverapi.core.UniqueIdService;
import com.technicaltest.driverapi.respositories.DriverDetailsRepository;
import com.technicaltest.driverapi.respositories.DriverDetailsRepositoryImpl;
import com.technicaltest.driverapi.utils.CSVFileUtils;
import com.technicaltest.driverapi.utils.CSVFileUtilsImpl;
import com.technicaltest.driverapi.utils.Validator;
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

    @Bean
    public DriverDetailsRepository driverDetailsRepository(CSVFileUtils csvFileUtils)
    {
        return new DriverDetailsRepositoryImpl(csvFileUtils);
    }

    @Bean
    public CSVFileUtils csvFileUtils()
    {
        return new CSVFileUtilsImpl();
    }

    @Bean
    public Validator validator()
    {
        return new Validator();
    }

}
