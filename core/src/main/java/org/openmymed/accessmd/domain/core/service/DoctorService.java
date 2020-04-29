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

    /**
     * Gets a list of all doctor's patients
     * @param doctorid the ID of the doctor to get the patients for
     * @return  a list of patients
     */
    public List<Patient> getDoctorPatients(Long doctorid);

    /**
     * Gets a list of all doctors
     * @return  a list of doctors
     */
    public List<Doctor> getDoctors();

    /**
     * Gets the medical profile of a patient, provided that they are assigned to the doctor
     * @param doctorId the id of the doctor to get the patient for
     * @param patientId the id of the patient to get
     * @return  the medical profile to the patient
     */
    public MedicalProfile getPatientProfile(Long doctorId, Long patientId);

    /**
     * Gets all patient symptoms not archived by the doctor, provided that the patient is assigned to them
     * @param doctorId the id of the doctor 
     * @param patientId the id of the patient
     * @return a list of symptoms
     */
    public List<Symptom> getUnarchivedPatientSymptoms(Long doctorId, Long patientId);

    /**
     * Get all patient answers not archived by the doctor, provided that the patient is assigned to them
     * @param doctorId the id of the doctor
     * @param patientId the id of the patient
     * @return a list of answers
     */
    public List<Answer> getUnarchivedPatientAnswers(Long doctorId, Long patientId);

    /**
     * Get all answers not archived by the doctor for all assigned patients
     * @param doctorId the id of the doctor
     * @return a list of answers
     */
    public List<Answer> getAllPatientsUnarchivedAnswers(Long doctorId);

    /**
     * Get all symptoms not archived by the doctor for all assigned patients
     * @param doctorId the id of the doctor
     * @return  a list of symptoms
     */
    public List<Symptom> getAllPatientsUnarchivedSymptoms(Long doctorId);

    /**
     * Asks a patient a question, provided they are assigned to this doctor
     * @param doctorId the id of the doctor
     * @param patientId the id of the patient
     * @param question the question to ask
     */
    public void askPatientAQuestion(Long doctorId, Long patientId, Question question);

    /**
     * Assigns a patient to a doctor
     * @param doctorId the id of the doctor to assign to 
     * @param patientId  the id of the patient to assign.
     */
    public void assignPatientToDoctor(Long doctorId, Long patientId);

    /**
     * Creates a doctor account from this user
     * @param user  the user to use to create the account for
     */
    public void createDoctor(User user);

    /**
     * Get a list of all questions asked to this patient
     * @param doctorId the Id of the doctor 
     * @param patientId the Id of the patient
     * @return a list of questions
     */
    public List<Question> getPatientQuestions(Long doctorId, Long patientId);

    /**
     * Stop asking a patient this question, provided the patient is assigned to the doctor, and the question was asked to the patient
     * @param doctorId the id of the doctor
     * @param patientId the id of the patient
     * @param questionId  the id of the question
     */
    public void stopAskingPatientAQuestion(Long doctorId, Long patientId, Long questionId);

    /**
     * Update a patient question, provided the patient is assigned to the doctor, and the question was asked to the patient
     * @param doctorId the Id of the doctor
     * @param patientId the id of the patient
     * @param questionId the id of the question
     * @param question  the update d question
     */
    public void updatePatientQuestion(Long doctorId, Long patientId, Long questionId, Question question);

    /**
     * Get the details of a question, provided the patient is assigned to the doctor, and the question was asked to the patient
     * @param doctorId the Id of the doctor
     * @param patientId the id of the patient
     * @param questionId the id of the question
     * @return  the question, if found
     */
    public Question getPatientQuestionDetails(Long doctorId, Long patientId, Long questionId);

    /**
     * Get the patient's basic information, provided they are assigned to this doctor
     * @param doctorId the id of the doctor
     * @param patientId the id of the patient
     * @return  the patient basic details
     */
    public Patient getPatient(Long doctorId, Long patientId);

    /**
     * Archive an answer, provided that the patient is assigned to the doctor, and the answer was given by the patient
     * @param doctorId the id of the doctor
     * @param patientId the id of the patient
     * @param answerId  the id of the answer
     */
    public void archiveAnswer(Long doctorId, Long patientId, Long answerId);

    /**
     * Archive a symptom, provided that the patient is assigned to the doctor, and the symptom was reported by the patient
     * @param doctorId
     * @param patientId
     * @param answerId 
     */
    public void archiveSymptom(Long doctorId, Long patientId, Long answerId);

    /**
     * Get all vitals measurements done by this patient, provided they are assigned to this doctor
     * @param doctorId the id of the doctor
     * @param patientId the id of the patient
     * @return a list of vitals measurements
     */
    public List<VitalsMeasurment> getPatientVitalsMeasurments(Long doctorId, Long patientId);

}
