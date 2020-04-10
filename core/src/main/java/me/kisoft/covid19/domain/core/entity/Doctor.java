/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
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

    @JsonProperty
    private String password;
    private String username;
    private UserRole userRole;
    private String telephoneNumber;

     public Doctor(User user) {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setUserRole(UserRole.ROLE_PATIENT);
        this.setTelephoneNumber(user.getTelephoneNumber());
    }
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getEntityName() {
        return "doctor";
    }

}
