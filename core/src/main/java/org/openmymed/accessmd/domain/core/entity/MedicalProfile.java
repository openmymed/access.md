/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.core.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.openmymed.accessmd.domain.entity.DomainEntity;
import org.openmymed.accessmd.domain.core.enums.Sex;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

/**
 *
 * @author tareq
 */
@Entity(name = "MedicalProfile")
@Table(name = "MEDICAL_PROFILE")
@Getter
@Setter
@Audited
@AuditOverride
public class MedicalProfile extends DomainEntity {

  private int age;
  private double height;
  private double weight;
  private Sex sex;
  private boolean g6pdDeficiency = false;
  private boolean respiratoryDiseases = false;
  private boolean diabetes = false;
  private boolean cardiovascularDiseases = false;
  private boolean obesity = false;
  @ElementCollection
  private List<String> medications = new ArrayList<>();

  @Override
  public String getEntityName() {
    return "medicalProfile";
  }

}
