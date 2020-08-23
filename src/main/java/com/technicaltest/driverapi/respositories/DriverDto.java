package com.technicaltest.driverapi.respositories;

import com.technicaltest.driverapi.utils.Constants;
import com.technicaltest.driverapi.core.Driver;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DriverDto extends Driver {

    public final String uniqueId;
    public final String creationDate;

    public DriverDto(String uniqueId, String firstName, String lastName, String dob, String creationDate) {
        super(firstName, lastName, dob);
        this.uniqueId = uniqueId;
        this.creationDate = creationDate;
    }
}
