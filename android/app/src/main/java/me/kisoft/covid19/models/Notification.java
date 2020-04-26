package me.kisoft.covid19.models;

import java.io.Serializable;

public class Notification implements Serializable {
    private String name;
    //Since the notification for patient going to be only for questions for now.
    //Replaced the Object with Question class.
    private Question data;

    public Notification() {
    }

    public Notification(String name, Question data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Question getData() {
        return data;
    }

    public void setData(Question data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "name='" + name + '\'' +
                ", data=" + data +
                '}';
    }
}
