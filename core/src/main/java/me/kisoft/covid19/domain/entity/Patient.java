/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import me.kisoft.covid19.domain.auth.entity.User;
import me.kisoft.covid19.domain.auth.entity.UserRole;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

/**
 *
 * @author tareq
 */
@Entity(name = "Patient")
@Table(name = "APP_USER")
@Audited
@AuditOverride
@Getter
@Setter
public class Patient extends DomainEntity {

    @JsonProperty
    private String password;
    private String username;
    private UserRole userRole;
    private String telephoneNumber;
    
    @Access(AccessType.PROPERTY)
    @OneToOne(cascade = CascadeType.ALL)
    MedicalProfile profile = new MedicalProfile();
    
    public Patient(User user) {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setUserRole(UserRole.ROLE_PATIENT);
        this.setTelephoneNumber(user.getTelephoneNumber());
    }

    public Patient(){
        
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
        return "patient";
    }

}
