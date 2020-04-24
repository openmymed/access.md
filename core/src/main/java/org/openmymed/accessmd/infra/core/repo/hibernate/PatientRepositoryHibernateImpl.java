/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.core.repo.hibernate;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.NoResultException;
import org.openmymed.accessmd.domain.core.repo.PatientRepository;
import org.openmymed.accessmd.domain.core.entity.Patient;
import org.openmymed.accessmd.domain.core.entity.Question;
import org.openmymed.accessmd.domain.core.entity.Reccomendation;
import org.openmymed.accessmd.infra.repo.hiberante.HibernateCrudRepository;

/**
 *
 * @author tareq
 */
public class PatientRepositoryHibernateImpl extends HibernateCrudRepository<Patient> implements PatientRepository {

    @Override
    public Class<Patient> getType() {
        return Patient.class;
    }

    @Override
    public List<Question> getPatientQuestions(Long patientId) {
        return this.findById(patientId).getQuestions();
    }

    @Override
    public List<Question> getUnAnsweredPatientQuestions(Long patientId) {
        return this.findById(patientId).getQuestions().stream().filter(question -> !question.isAnswered()).collect(Collectors.toList());
    }

    @Override
    public List<Reccomendation> getPatientReccomendations(Long patientId) {
        return  this.findById(patientId).getReccomendations();
    }

    @Override
    public Patient getPatientByDoctorAndId(Long patientId, Long doctorId) {
        try{
            return getEm().createNamedQuery("Patient.byDoctorAndId",Patient.class)
                    .setParameter("patient_id", patientId)
                    .setParameter("doctor_id", doctorId)
                    .getSingleResult();
        }catch(NoResultException ex){
            return null;
        }
    }


}
