package me.kisoft.covid19.service;

public class PatientServiceDelegate implements PatientService {
    private final PatientService service = new PatientServiceRealImpl();

    @Override
    public void doStuff() {
        service.doStuff();
    }

    @Override
    public void undoStuff() {
        service.undoStuff();
    }
}
