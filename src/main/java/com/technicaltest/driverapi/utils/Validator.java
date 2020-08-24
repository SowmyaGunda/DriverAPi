package com.technicaltest.driverapi.utils;
import com.technicaltest.driverapi.core.Driver;

import java.text.SimpleDateFormat;

public class Validator {

    public boolean isValidData(Driver driver) throws InvalidDataException {
        if (driver.firstname == null || driver.firstname.isEmpty()) {
            throw new InvalidDataException("'firstname' should not be null/empty");
        }
        if (driver.lastname == null || driver.lastname.isEmpty()) {
            throw new InvalidDataException("'lastname' should not be null or empty");
        }
        if (driver.date_of_birth == null || driver.date_of_birth.isEmpty()) {
            throw new InvalidDataException("'date_of_birth' should not be null or empty");
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DateFormat);
            dateFormat.setLenient(false);
            try {
               dateFormat.format(dateFormat.parse(driver.date_of_birth));
            } catch (Exception e) {
                throw new InvalidDataException(String.format("'date_of_birth' should be in '%s'", Constants.DateFormat));
            }
        }
        return true;
    }
}
