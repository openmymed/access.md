/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import me.kisoft.covid19.domain.core.enums.QuestionType;
import me.kisoft.covid19.domain.entity.DomainEntity;

/**
 *
 * @author tareq
 */
@Entity(name="Question")
@Table(name= "QUESTION")
@Getter
@Setter
public class Question extends DomainEntity {

    private String question;
    private String answer;
    
    @JsonIgnore
    @ManyToOne
    private Patient patient;
    private QuestionType type;
    private boolean answered;
    
    public void answerQuestion(String answer){
        this.answer = answer;
        this.answered = true;
    }
    @Override
    public String getEntityName() {
        return "question";
    }
    
}
