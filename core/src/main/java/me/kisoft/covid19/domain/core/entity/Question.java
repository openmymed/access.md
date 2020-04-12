/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import me.kisoft.covid19.domain.core.enums.QuestionType;
import me.kisoft.covid19.domain.entity.DomainEntity;

/**
 *
 * @author tareq
 */
@Entity(name = "Question")
@Table(name = "QUESTION")
@Getter
@Setter
public class Question extends DomainEntity {

    private String question;
    
    @OneToMany
    @JsonIgnore
    private List<Answer> answers = new ArrayList<>();
    @JsonIgnore
    @ManyToOne
    private Patient patient;
    private QuestionType type;
    private boolean answered;
    @Temporal(TemporalType.TIMESTAMP)
    private Date answeredOn;
    @ElementCollection
    private List<RecurringQuestionAskingTimes> recurrance = new ArrayList<>();
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private boolean recurring;

    public void answerQuestion(Answer answer) {
        if(this.answers == null){
            this.answers = new ArrayList<>();
        }
        answer.setQuestion(this);
        this.answers.add(answer);
        this.answered = true;
        this.answeredOn = new Date();
    }

    @Override
    public String getEntityName() {
        return "question";
    }

}
