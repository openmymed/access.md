/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.auth.entity;

import me.kisoft.covid19.domain.auth.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import me.kisoft.covid19.domain.entity.DomainEntity;

/**
 *
 * @author tareq
 */
@Getter
@Setter
@Entity(name = "User")
@Table(name = "APP_USER")
@NamedQueries({
    @NamedQuery(name = "User.byUsername", query = "SELECT u from User u where u.username=(:username)")})
public class User extends DomainEntity {

    @JsonProperty
    private String password;
    private String username;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private String telephoneNumber;
   
    
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
        return "user";
    }
}
