package me.kisoft.covid19.services;

import me.kisoft.covid19.models.Patient;

public interface PatientService {
    Patient login(String phone, String password);

    void register(Patient patient);
}
