package me.kisoft.covid19.models;

import java.io.Serializable;
import java.util.List;

public class MedicalProfile implements Serializable {
    private int age;
    private double height;
    private double weight;
    private Sex sex;
    private List<String> medications;
    private boolean g6pdDeficiency;
    private boolean respiratoryDiseases;
    private boolean diabetes;
    private boolean cardiovascularDiseases;
    private boolean obesity;

    public MedicalProfile() {
    }

    public MedicalProfile(int age, double height, double weight, Sex sex, List<String> medications, boolean g6pdDeficiency, boolean respiratoryDiseases, boolean diabetes, boolean cardiovascularDiseases, boolean obesity) {
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.sex = sex;
        this.medications = medications;
        this.g6pdDeficiency = g6pdDeficiency;
        this.respiratoryDiseases = respiratoryDiseases;
        this.diabetes = diabetes;
        this.cardiovascularDiseases = cardiovascularDiseases;
        this.obesity = obesity;
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

    public boolean isG6pdDeficiency() {
        return g6pdDeficiency;
    }

    public void setG6pdDeficiency(boolean g6pdDeficiency) {
        this.g6pdDeficiency = g6pdDeficiency;
    }

    public boolean isRespiratoryDiseases() {
        return respiratoryDiseases;
    }

    public void setRespiratoryDiseases(boolean respiratoryDiseases) {
        this.respiratoryDiseases = respiratoryDiseases;
    }

    public boolean isDiabetes() {
        return diabetes;
    }

    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }

    public boolean isCardiovascularDiseases() {
        return cardiovascularDiseases;
    }

    public void setCardiovascularDiseases(boolean cardiovascularDiseases) {
        this.cardiovascularDiseases = cardiovascularDiseases;
    }

    public boolean isObesity() {
        return obesity;
    }

    public void setObesity(boolean obesity) {
        this.obesity = obesity;
    }

    @Override
    public String toString() {
        return "MedicalProfile{" +
                "age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", sex=" + sex +
                ", medications=" + medications +
                '}';
    }
}
