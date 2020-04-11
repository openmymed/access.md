/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import me.kisoft.covid19.domain.auth.enums.SecurityCodeStatus;
import me.kisoft.covid19.domain.entity.DomainEntity;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author tareq
 */
@Entity(name = "SecurityCode")
@Table(name = "SECURITY_CODE")
@Getter
@Setter
@NamedQueries({
    @NamedQuery(name = "SecurityCode.byCodeAndStatus", query = "SELECT sc from SecurityCode sc WHERE sc.code = (:code) AND sc.status=(:status)"),
    @NamedQuery(name="SecurityCode.validCodesToExpire", query="SELECT sc from SecurityCode sc WHERE sc.validUntil < (:time) AND sc.status='VALID' "),
    @NamedQuery(name="SecurityCode.byUserAndStatus", query="SELECT sc FROM SecurityCode sc WHERE sc.belongsTo= (:user_id) AND sc.status=(:status)")
})
public class SecurityCode extends DomainEntity {

    @JsonIgnore
    private long consumedBy;
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private SecurityCodeStatus status;
    @JsonIgnore
    private long belongsTo;
    private String code;
    @Temporal(TemporalType.TIME)
    private Date validUntil;
    @JsonIgnore
    @Temporal(TemporalType.TIME)
    private Date consumedOn;
    private static final long FIVE_MINUTES = 60l * 5l * 1000l;

    public SecurityCode() {
        status = SecurityCodeStatus.VALID;
        code = generateCode();
        validUntil = generateValidUntil();
    }

    public SecurityCode(long belongsTo) {
        this();
        this.belongsTo = belongsTo;
    }

    private String generateCode() {
        return RandomStringUtils.random(8, true, true);
    }

    private Date generateValidUntil() {
        Date now = new Date();
        now.setTime(now.getTime() + FIVE_MINUTES);
        return now;
    }

    public void consume(long consumerId) {
        status = SecurityCodeStatus.CONSUMED;
        consumedBy = consumerId;
        consumedOn = new Date();
    }

    public void invalidate(){
         status = SecurityCodeStatus.INVALIDATED;
    }
    
    public void timeOut(){
        status =  status = SecurityCodeStatus.TIMEOUT;
    }

    @Override

    public String getEntityName() {
        return "securityCode";
    }

}
