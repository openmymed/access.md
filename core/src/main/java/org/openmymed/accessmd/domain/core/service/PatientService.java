/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.core.service;

import java.util.List;
import org.openmymed.accessmd.domain.auth.entity.User;
import org.openmymed.accessmd.domain.core.entity.Doctor;
import org.openmymed.accessmd.domain.core.entity.MedicalProfile;
import org.openmymed.accessmd.domain.core.entity.Question;
import org.openmymed.accessmd.domain.core.entity.Reply;
import org.openmymed.accessmd.domain.core.entity.Symptom;
import org.openmymed.accessmd.domain.core.entity.VitalsMeasurment;

/**
 *
 * @author tareq
 */
public interface PatientService {

    /**
     * Updates a patient's medical profile
     *
     * @param patientId the id of the patient
     * @param profile the updated patient profile
     */
    void updatePatientMedicalProfile(Long patientId, MedicalProfile profile);

    /**
     *
     * @param user the user to create the patient from
     */
    void createPatient(User user);

    /**
     * Get the medical profile of the patient
     * @param patientId the id of the patient 
     * @return the medical profile to the patient
     */
    public MedicalProfile getPatientMedicalProfile(Long patientId);

    /**
     * Get the unanswered questions for a patient
     * @param patientId the id of the patient
     * @return  the list of unanswered questions
     */
    public List<Question> getPatientUnansweredQuestions(Long patientId);

    /**
     * Get the list of doctor replies for patient
     * @param patientId the id of the patient
     * @return a list of replies
     */
    public List<Reply> getDoctorRepliesForPatient(Long patientId);

    /**
     * Answer a question, provided it was asked to this patient
     * @param patientId the id of the patient 
     * @param questionId the id of the question
     * @param answer  the answer as a string
     */
    public void answerPatientQuestion(Long patientId, Long questionId, String answer);

    /**
     * Reports a new symptom by a patient
     * @param patientId the patient reporting the symptoim
     * @param symptom the symptom to be reported
     */
    public void reportNewPatientSymptom(Long patientId, Symptom symptom);

    /**
     * Gets the doctor assigned to this patient
     * @param patientId the id of the patient
     * @return  the assigned doctor
     */
    public Doctor getPatientAssignedDoctor(Long patientId);

    /**
     *  Reports a new vitals measurement by a patient
     * @param patientId the patientId
     * @param vitalsMeasurment  the reported vitals measurement
     */
    public void reportNewVitalsMeasurment(Long patientId, VitalsMeasurment vitalsMeasurment);

}
