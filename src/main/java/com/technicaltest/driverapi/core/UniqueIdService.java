package com.technicaltest.driverapi.core;

import java.util.UUID;

public class UniqueIdService {
    public String GetNewId()
    {
        return UUID.randomUUID().toString();
    }
}
