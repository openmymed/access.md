package me.kisoft.covid19.models;

public class Patient {
    private String phone;
    private String password;
    private String firstName;
    private String lastName;
    private Sex sex;

    public Patient() {
    }

    public Patient(String phone, String password, String firstName, String lastName, Sex sex) {
        this.phone = phone;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
