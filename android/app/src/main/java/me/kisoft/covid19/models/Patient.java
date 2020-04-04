package me.kisoft.covid19.models;

public class Patient {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public Patient(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
