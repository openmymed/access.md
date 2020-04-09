package me.kisoft.covid19.models;

import java.io.Serializable;

public class Patient implements Serializable {
    //Any fields you don't want serialized to JSON in general you should use the "transient" modifier,
    private transient int id;
    private String username;
    private String password;
    private UserRole userRole;
    private String telephoneNumber;

    public Patient() {
    }

    public Patient(String username, String password, String telephoneNumber) {
        this.username = username;
        this.password = password;
        this.telephoneNumber = telephoneNumber;
    }

    public Patient(String username, String password, UserRole userRole, String telephoneNumber) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.telephoneNumber = telephoneNumber;
    }

    public Patient(int id, String username, String password, UserRole userRole, String telephoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.telephoneNumber = telephoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
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
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }
}
