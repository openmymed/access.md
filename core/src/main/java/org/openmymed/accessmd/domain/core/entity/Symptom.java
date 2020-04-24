/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.openmymed.accessmd.domain.entity.DomainEntity;

/**
 *
 * @author tareq
 */
@Entity(name = "Symptom")
@Table(name = "SYMPTOM")
@Getter
@Setter
public class Symptom extends DomainEntity {

  private String symptomCode;
  @ManyToOne
  @JsonIgnore
  private Patient patient;

  private String note;
  private boolean seen = false;
  private Date seenOn;

  public void setSeen(boolean seen) {
    if (seen == true) {
      seenOn = new Date();
    }
    this.seen = seen;
  }

  @Override
  public String getEntityName() {
    return "symptom";
  }

}
