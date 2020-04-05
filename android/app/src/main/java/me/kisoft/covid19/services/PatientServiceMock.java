package me.kisoft.covid19.services;

import java.util.ArrayList;
import java.util.Arrays;

import me.kisoft.covid19.models.Patient;
import me.kisoft.covid19.models.Sex;

public class PatientServiceMock implements PatientService {
    //todo check if this is the correct impl for the

    static ArrayList<Patient> patients = new ArrayList<>(Arrays.asList(new Patient("Admin", "Admin", "Ahmad", "Ahmad", Sex.Male),
            new Patient("0591234561", "Admin", "Mohammad", "Mohammad", Sex.Male),
            new Patient("0592234561", "Admin", "Mousa", "Mousa", Sex.Male),
            new Patient("0593234561", "Admin", "Ibraheem", "Ibraheem", Sex.Male),
            new Patient("0594234561", "Admin", "Majed", "Majed", Sex.Male)));

    @Override
    public Patient login(String phone, String password) {
        //do stuff here
        for (Patient patient : patients) {
            if (patient.getPhone().equals(phone) && patient.getPassword().equals(password)) {
                return patient;
            }
        }
        return null;
    }

    @Override
    public void register(Patient patient) {
        //do stuff here
        patients.add(patient);
    }
}
