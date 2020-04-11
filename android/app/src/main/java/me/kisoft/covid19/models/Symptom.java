package me.kisoft.covid19.models;

import java.io.Serializable;

public class Symptom implements Serializable {
    private String code;
    private String title;

    public Symptom() {
    }

    public Symptom(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return title;
    }
}
