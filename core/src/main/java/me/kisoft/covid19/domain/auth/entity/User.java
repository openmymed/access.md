/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import me.kisoft.covid19.domain.event.DomainEvent;
import me.kisoft.covid19.domain.event.EventBus;
import me.kisoft.covid19.domain.entity.DomainEntity;
/**
 *
 * @author tareq
 */
@Getter
@Setter
@Entity(name="User")
@Table(name="APP_USER")
public class User extends DomainEntity {

  @JsonProperty
  private String password;
  private String username;
  private UserRole userRole;
  private String telephoneNumber;

  @JsonIgnore
  public String getPassword(){
      return this.password;
  }
  
  @JsonProperty
  public void setPassword(String password){
      this.password = password;
  }
  @Override
  public void postDeleted() {
    EventBus.getInstance().post(new DomainEvent("userDeleted", this));
  }

  @Override
  public void postSaved() {
    EventBus.getInstance().post(new DomainEvent("userSaved", this));
  }

  @Override
  public void postUpdated() {
    EventBus.getInstance().post(new DomainEvent("userUpdated", this));
  }
}
