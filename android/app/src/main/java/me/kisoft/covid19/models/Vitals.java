package me.kisoft.covid19.models;

public class Vitals {

    //Breathing Rate
    private int  breathingRate;

    //HeartBeat Per minute
    private int  heartBeatsPerMinute;

    //Systolic pressure
    private int  systolicPressure;

    //Diastolic pressure
    private int  diastolicPressure;

    //Blood Oxygination
    private int  bloodOxygination;

    Vitals(){}

    public Vitals(int breathingRate, int heartBeatsPerMinute, int systolicPressure, int diastolicPressure, int bloodOxygination) {
        this.breathingRate = breathingRate;
        this.heartBeatsPerMinute = heartBeatsPerMinute;
        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
        this.bloodOxygination = bloodOxygination;
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

    public int getBloodOxygination() {
        return bloodOxygination;
    }

    public void setBloodOxygination(int bloodOxygination) {
        this.bloodOxygination = bloodOxygination;
    }
}
