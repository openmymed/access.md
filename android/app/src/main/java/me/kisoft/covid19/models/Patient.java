package me.kisoft.covid19.models;

import java.io.Serializable;

public class Patient implements Serializable {
    //Any fields you don't want serialized to JSON in general you should use the "transient" modifier,
    private transient int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private UserRole userRole;
    private String telephoneNumber;
    private MedicalProfile profile;

    public Patient() {
    }

    public Patient(String username, String password, String telephoneNumber,String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.telephoneNumber = telephoneNumber;
        this.userRole = UserRole.ROLE_PATIENT;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public Patient(int id, String username, String password, String firstName, String lastName, String telephoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = UserRole.ROLE_PATIENT;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public MedicalProfile getProfile() {
        return profile;
    }

    public void setProfile(MedicalProfile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userRole=" + userRole +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", profile=" + profile +
                '}';
    }
}
