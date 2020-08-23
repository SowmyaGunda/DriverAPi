package com.technicaltest.driverapi.controllers;

import com.technicaltest.driverapi.core.Driver;
import com.technicaltest.driverapi.core.DriverDetailsService;
import com.technicaltest.driverapi.respositories.DriverDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Collection<DriverDto>> GetAllDrivers() {
        try
        {
            return ResponseEntity.ok(this.driverDetailsService.getAllDrivers());
        }
        catch(Exception exception)
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @GetMapping(value = "/drivers/byDate", produces = "application/json")
    public ResponseEntity<Collection<DriverDto>> GetDriversByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {

        if(date == null)
        {
            return ResponseEntity.badRequest().body(null);
        }

        try
        {
            return ResponseEntity.ok(this.driverDetailsService.getDriversByDate(date));
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
