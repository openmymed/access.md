/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.openmymed.accessmd.domain.entity.DomainEntity;

/**
 *
 * @author tareq
 */
@Entity(name="Reccomendation")
@Table(name= "RECCOMENDATION")
@Getter
@Setter
public class Reccomendation extends DomainEntity{

    private String reccomendation;
    
    @OneToMany
    private List<Question> questions = new ArrayList<>();
    
    @ManyToOne
    @JsonIgnore
    private Patient patient;
    
    public void inResponseToQuestion(Question question){
        if(questions == null){
            questions = new ArrayList<>();
        }
        questions.add(question);
    }
    @Override
    public String getEntityName() {
       return "reccomendation";
    }
    
}
