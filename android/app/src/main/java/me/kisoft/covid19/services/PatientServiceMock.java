package me.kisoft.covid19.services;

import me.kisoft.covid19.models.Patient;

public class PatientServiceMock implements PatientService {
    //todo check if this is the correct impl for the pattern

    @Override
    public void Login(String username, String password) {
        //do stuff here
        String user = "Admin";
        String pass = "Admin";

    }

    @Override
    public void Register(Patient patient) {
        //do stuff here
    }
}
