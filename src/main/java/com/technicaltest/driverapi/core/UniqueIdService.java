package com.technicaltest.driverapi.core;

import java.util.UUID;

public class UniqueIdService {
    public String getNewId()
    {
        return UUID.randomUUID().toString();
    }
}
