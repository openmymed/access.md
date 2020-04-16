/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.kisoft.covid19.domain.auth.entity.User;
import me.kisoft.covid19.domain.auth.enums.UserRole;
import me.kisoft.covid19.domain.entity.DomainEntity;

/**
 *
 * @author tareq
 */
@Entity(name = "Doctor")
@Table(name = "APP_USER")
@Getter
@Setter
@NoArgsConstructor
public class Doctor extends DomainEntity {

  @JsonIgnore
  private String password;
  private String username;
  @Enumerated(EnumType.STRING)
  private UserRole userRole;
  private String telephoneNumber;
  private String firstName;
  private String lastName;
  @JsonIgnore
  @OneToMany
  private List<Patient> patients = new ArrayList<>();

  public Doctor(User user) {
    this.setUsername(user.getUsername());
    this.setPassword(user.getPassword());
    this.setUserRole(UserRole.ROLE_DOCTOR);
    this.setTelephoneNumber(user.getTelephoneNumber());
    this.setFirstName(user.getFirstName());
    this.setLastName(user.getLastName());
  }

  @JsonIgnore
  public String getPassword() {
    return this.password;
  }

  @JsonProperty
  public void setPassword(String password) {
    this.password = password;
  }

  public void addPatient(Patient patient) {
    if (patients == null) {
      patients = new ArrayList<>();
    }
    patient.setDoctor(this);
    patients.add(patient);
  }

  @Override
  public String getEntityName() {
    return "doctor";
  }

}
