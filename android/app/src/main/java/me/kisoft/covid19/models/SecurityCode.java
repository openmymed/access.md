package me.kisoft.covid19.models;

import java.io.Serializable;
import java.util.Date;

public class SecurityCode implements Serializable {
    private int id;
    private Date creationDate;
    private Date updateDate;
    private Date validUntil;
    private String code;
    private String entityName;

    public SecurityCode() {
    }

    public SecurityCode(int id, Date creationDate, Date updateDate, Date validUntil, String code, String entityName) {
        this.id = id;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.validUntil = validUntil;
        this.code = code;
        this.entityName = entityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    @Override
    public String toString() {
        return "SecurityCode{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", validUntil=" + validUntil +
                ", code='" + code + '\'' +
                ", entityName='" + entityName + '\'' +
                '}';
    }
}
