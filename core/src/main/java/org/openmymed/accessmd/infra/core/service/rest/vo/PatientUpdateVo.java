/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.core.service.rest.vo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.openmymed.accessmd.domain.core.entity.Answer;
import org.openmymed.accessmd.domain.core.entity.Patient;
import org.openmymed.accessmd.domain.core.entity.Symptom;

/**
 *
 * @author tareq
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientUpdateVo {

  private long patientId;
  private long entityId;
  private String patientName;
  private PatientUpdateType type;
  private Date time;
  private String value;

  public PatientUpdateVo(Patient p, Answer answer) {
    patientId = p.getId();
    entityId = answer.getId();
    patientName = p.getFirstName() + " " + p.getLastName();
    type = PatientUpdateType.UNSEEN_ANSWER;
    time = answer.getCreationDate();
    value = answer.getQuestion().getQuestion();
  }

  public PatientUpdateVo(Patient p, Symptom symptom) {
    patientId = p.getId();
    entityId = symptom.getId();
    patientName = p.getFirstName() + " " + p.getLastName();
    type = PatientUpdateType.UNSEEN_SYMPTOM;
    time = symptom.getCreationDate();
    value = symptom.getSymptomCode();
  }
}
