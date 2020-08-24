package com.technicaltest.driverapi.controllers;

import com.technicaltest.driverapi.core.Driver;
import com.technicaltest.driverapi.core.DriverDetailsService;
import com.technicaltest.driverapi.respositories.DriverDto;
import com.technicaltest.driverapi.utils.InvalidDataException;
import com.technicaltest.driverapi.utils.Validator;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;

@RestController()
@ApiOperation(value = "Controller to handle operations on Drivers data")
public class DriverController {

    private final DriverDetailsService driverDetailsService;
    private final Validator validator;

    @Autowired
    public DriverController(DriverDetailsService driverDetailsService, Validator validator) {
        this.driverDetailsService = driverDetailsService;
        this.validator = validator;
    }

    @GetMapping(value = "/drivers", produces = "application/json")
    @ApiOperation("Get list of all drivers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully processed the request"),
            @ApiResponse(code = 417, message = "Any error occurred while processing request")})
    public ResponseEntity<Collection<DriverDto>> GetAllDrivers() {
        try {
            return ResponseEntity.ok(this.driverDetailsService.getAllDrivers());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @GetMapping(value = "/drivers/byDate", produces = "application/json")
    @ApiOperation(value = "Get list of drivers who were created after given date.[Given date no included]")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully process the request"),
            @ApiResponse(code = 417, message = "Any error occurred while processing request")})
    @ApiImplicitParam(name = "date", format = "yyyy-MM-dd")
    public ResponseEntity<Collection<DriverDto>> GetDriversByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {

        if (date == null) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            return ResponseEntity.ok(this.driverDetailsService.getDriversByDate(date));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @PostMapping(value = "/driver/create", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Create a new driver with provided details")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully processed the request"),
            @ApiResponse(code = 400, message = "invalid request body"),
            @ApiResponse(code = 417, message = "Any error occurred while processing request")})
    public ResponseEntity AddDriver(@RequestBody Driver driver) {
        try {
            if (validator.isValidData(driver)) {
                this.driverDetailsService.saveDetails(driver);
            }
            return ResponseEntity.ok().build();
        }
        catch (InvalidDataException invalidDataException)
        {
            return ResponseEntity.badRequest().body(invalidDataException.getMessage());
        }
        catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }
}
