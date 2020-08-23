package com.technicaltest.driverapi.core;

public class Driver {
    public final String firstname;
    public final String lastname;
    public final String date_of_birth;

    public Driver(String firstName, String lastName, String dob)
    {
        this.firstname = firstName;
        this.lastname = lastName;
        this.date_of_birth = dob;
    }
}
