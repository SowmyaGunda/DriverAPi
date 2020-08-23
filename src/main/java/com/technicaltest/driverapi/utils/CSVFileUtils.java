package com.technicaltest.driverapi.utils;

import java.io.IOException;
import java.util.Collection;

public interface CSVFileUtils {
    void writeToCSVFile(Collection<String> rowData) throws IOException;

    Collection<String[]> readFromCSVFile() throws IOException;
}
