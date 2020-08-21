package com.technicaltest.driverapi.controllers;

import com.technicaltest.driverapi.core.Driver;
import com.technicaltest.driverapi.core.DriverDetailsService;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Collection;
import java.util.Date;

@RestController
public class DriverController {

    private final DriverDetailsService driverDetailsService;

    @Autowired
    public DriverController(DriverDetailsService driverDetailsService)
    {
        this.driverDetailsService = driverDetailsService;
    }

    @GetMapping("/drivers")
    public Collection<Driver> GetDrivers(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Nullable Date date) {
        return this.driverDetailsService.getDrivers(date);
    }
}
