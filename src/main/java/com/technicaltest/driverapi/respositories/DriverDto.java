package com.technicaltest.driverapi.respositories;

import com.technicaltest.driverapi.core.Driver;
import com.technicaltest.driverapi.core.UniqueIdService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DriverDto extends Driver {
    public final static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    public final String uniqueId;
    public final String creationDate;

    public DriverDto(String firstName, String lastName, String dob) {
        super(firstName, lastName, dob);
        uniqueId = new UniqueIdService().getNewId();
        creationDate = formatter.format(new Date());
    }

    public DriverDto(String uniqueId, String firstName, String lastName, String dob, String creationDate) {
        super(firstName, lastName, dob);
        this.uniqueId = uniqueId;
        this.creationDate = creationDate;
    }
}
