/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.core.service.rest.vo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.kisoft.covid19.domain.core.entity.Answer;
import me.kisoft.covid19.domain.core.entity.Patient;
import me.kisoft.covid19.domain.core.entity.Symptom;

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
  private String note;

  public PatientUpdateVo(Patient p, Answer answer) {
    patientId = p.getId();
    entityId = answer.getId();
    patientName = p.getFirstName() + " " + p.getLastName();
    type = PatientUpdateType.UNSEEN_ANSWER;
    time = answer.getCreationDate();
  }

  public PatientUpdateVo(Patient p, Symptom symptom) {
    patientId = p.getId();
    entityId = symptom.getId();
    patientName = p.getFirstName() + " " + p.getLastName();
    type = PatientUpdateType.UNSEEN_UPDATE;
    time = symptom.getCreationDate();
  }
}
