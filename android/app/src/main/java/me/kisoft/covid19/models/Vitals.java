package me.kisoft.covid19.models;

import java.io.Serializable;

public class Vitals implements Serializable {

    //Breathing Rate
    private int  breathingRate;

    //HeartBeat Per minute
    private int  heartBeatsPerMinute;

    //Systolic pressure
    private int  systolicPressure;

    //Diastolic pressure
    private int  diastolicPressure;

    //Blood Oxygination
    private int  bloodOxygenation;

    Vitals(){}

    public Vitals(int breathingRate, int heartBeatsPerMinute, int systolicPressure, int diastolicPressure, int bloodOxygenation) {
        this.breathingRate = breathingRate;
        this.heartBeatsPerMinute = heartBeatsPerMinute;
        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
        this.bloodOxygenation = bloodOxygenation;
    }

    public int getBreathingRate() {
        return breathingRate;
    }

    public void setBreathingRate(int breathingRate) {
        this.breathingRate = breathingRate;
    }

    public int getHeartBeatsPerMinute() {
        return heartBeatsPerMinute;
    }

    public void setHeartBeatsPerMinute(int heartBeatsPerMinute) {
        this.heartBeatsPerMinute = heartBeatsPerMinute;
    }

    public int getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(int systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public int getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(int diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public int getBloodOxygenation() {
        return bloodOxygenation;
    }

    public void setBloodOxygenation(int bloodOxygenation) {
        this.bloodOxygenation = bloodOxygenation;
    }

    @Override
    public String toString() {
        return "Vitals{" +
                "breathingRate=" + breathingRate +
                ", heartBeatsPerMinute=" + heartBeatsPerMinute +
                ", systolicPressure=" + systolicPressure +
                ", diastolicPressure=" + diastolicPressure +
                ", bloodOxygination=" + bloodOxygenation +
                '}';
    }
}
