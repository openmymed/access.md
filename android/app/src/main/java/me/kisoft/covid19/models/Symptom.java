package me.kisoft.covid19.models;


public class Symptom {
    private String symptomCode;
    private String note;

    public Symptom() {
    }

    public Symptom(String symptomCode, String note) {
        this.symptomCode = symptomCode;
        this.note = note;
    }

    public String getSymptomCode() {
        return symptomCode;
    }

    public void setSymptomCode(String symptomCode) {
        this.symptomCode = symptomCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Symptom{" +
                "symptomCode='" + symptomCode + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
