package me.kisoft.covid19.models;

public class Symptom {
    private String symptom;
    private String notes;

    public Symptom() {
    }

    public Symptom(String symptom, String notes) {
        this.symptom = symptom;
        this.notes = notes;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
