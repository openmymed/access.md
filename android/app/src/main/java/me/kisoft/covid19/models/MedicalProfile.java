package me.kisoft.covid19.models;

import java.io.Serializable;
import java.util.List;

public class MedicalProfile implements Serializable {
    private int age;
    private double height;
    private double weight;
    private Sex sex;
    private List<String> medications;
    private List<String> medicalFlags;

    public MedicalProfile() {
    }

    public MedicalProfile(int age, int height, int weight, Sex sex, List<String> medications, List<String> medicalFlags) {
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.sex = sex;
        this.medications = medications;
        this.medicalFlags = medicalFlags;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getMedicalFlags() {
        return medicalFlags;
    }

    public void setMedicalFlags(List<String> medicalFlags) {
        this.medicalFlags = medicalFlags;
    }

    @Override
    public String toString() {
        return "MedicalProfile{" +
                "age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", sex=" + sex +
                ", medications=" + medications +
                ", medicalFlags=" + medicalFlags +
                '}';
    }
}
