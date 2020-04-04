package me.kisoft.covid19.services;

import me.kisoft.covid19.models.Patient;

public interface PatientService {
    void Login(String username, String password);

    void Register(Patient patient);
}
