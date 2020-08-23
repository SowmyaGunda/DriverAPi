package com.technicaltest.driverapi;

import com.technicaltest.driverapi.utils.CSVFileUtils;
import com.technicaltest.driverapi.utils.CSVFileUtilsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Configuration
@ComponentScan("com.technicaltest.driverapi")
public class TestConfiguration {

    @Bean
    public CSVFileUtils csvFileUtils()
    {
        return new CSVFileUtilsImpl(){
            @Override
            public void writeToCSVFile(Collection<String> csvRowData) throws IOException {
                return;
            }

            @Override
            public Collection<String[]> readFromCSVFile() throws IOException {
                ArrayList<String[]> csvContent= new ArrayList<>();
                csvContent.add(new String[]{"UniqueId-1", "Driver-1-First-Name", "Driver-1-Last-Name","1980-12-20", "2020-08-12"});
                csvContent.add(new String[]{"UniqueId-2", "Driver-2-First-Name", "Driver-3-Last-Name","1971-08-01", "2020-09-01"});
                return csvContent;
            }
        };
    }

}
