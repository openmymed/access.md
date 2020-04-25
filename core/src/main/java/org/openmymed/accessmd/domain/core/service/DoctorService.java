/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.core.service;

import java.util.Date;
import java.util.List;
import org.openmymed.accessmd.domain.auth.entity.User;
import org.openmymed.accessmd.domain.core.entity.Answer;
import org.openmymed.accessmd.domain.core.entity.Doctor;
import org.openmymed.accessmd.domain.core.entity.MedicalProfile;
import org.openmymed.accessmd.domain.core.entity.Patient;
import org.openmymed.accessmd.domain.core.entity.Question;
import org.openmymed.accessmd.domain.core.entity.Symptom;
import org.openmymed.accessmd.domain.core.entity.VitalsMeasurment;

/**
 *
 * @author tareq
 */
public interface DoctorService {

    public List<Patient> getDoctorPatients(Long doctorid);

    public List<Doctor> getDoctors();

    public MedicalProfile getPatientProfile(Long doctorId, Long patientId);

    public List<Symptom> getUnarchivedPatientSymptoms(Long doctorId, Long patientId);

    public List<Answer> getUnarchivedPatientAnswers(Long doctorId, Long patientId);

    public List<Answer> getAllPatientsUnseenAnswers(Long doctorId);

    public List<Symptom> getAllPatientsUnseenSymptoms(Long doctorId);

    public void addQuestionForPatient(Long doctorId, Long patientId, Question question);

    public void addPatient(Long doctorId, Long patientId);

    public void createDoctor(User user);

    public List<Question> getPatientQuestions(Long id, Long patientId);

    public void removePatientQuestion(Long id, Long patientId, Long questionId);

    public void updatePatientQuestion(Long id, Long patientId, Long questionId, Question question);

    public Question getPatientQuestion(Long id, Long patientId, Long questionId);

    public Patient getPatient(Long id, Long get);

    public void archiveAnswer(Long doctorId, Long patientId, Long answerId);

    public void archiveSymptom(Long doctorId, Long patientId, Long answerId);

    public List<VitalsMeasurment> getPatientVitalsMeasurments(Long doctorId, Long patientId);

}
