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
@Entity(name = "RecurringQuestion")
@Table(name = "RECURRING_QUESTION")
@Getter
@Setter
public class RecurringQuestion extends DomainEntity {

    private String question;
    private String answer;
    @JsonIgnore
    @ManyToOne
    private Patient patient;
    private QuestionType type;
    @ElementCollection
    private List<RecurringQuestionAskingTimes> recurrance = new ArrayList<>();
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Override
    public String getEntityName() {
        return "recurringQuestion";
    }

}
