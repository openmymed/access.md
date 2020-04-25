/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.core.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
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
@Entity(name = "Answer")
@Table(name = "ANSWERS")
@Getter
@Setter
public class Answer extends DomainEntity {

    @ManyToOne
    private Question question;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>(); 
    private String answer;
    private boolean archived = false;
    private Date archiveDate;

    public void archive() {
        archiveDate = new Date();
        this.archived = true;
        this.queueEvent("answerArchived", archiveDate);
    }

    public void reply(String doctorsReply) {
        Reply reply = new Reply();
        reply.setReply(doctorsReply);
        replies.add(reply);
        queueEvent("answerRepliedTo",reply);
    }

    @Override
    public String getEntityName() {
        return "answer";
    }

}
