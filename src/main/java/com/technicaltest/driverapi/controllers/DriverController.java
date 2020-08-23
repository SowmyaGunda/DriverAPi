package com.technicaltest.driverapi.controllers;

import com.technicaltest.driverapi.core.Driver;
import com.technicaltest.driverapi.core.DriverDetailsService;
import com.technicaltest.driverapi.respositories.DriverDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "/drivers", produces = "application/json")
    public ResponseEntity<Collection<DriverDto>> GetDrivers(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                             @Nullable Date date) {
        try
        {
            return ResponseEntity.ok(this.driverDetailsService.getDrivers(date));
        }
        catch(Exception exception)
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @PostMapping(value= "/driver/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> AddDriver(@RequestBody Driver driver)
    {
        try
        {
            this.driverDetailsService.saveDetails(driver);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
