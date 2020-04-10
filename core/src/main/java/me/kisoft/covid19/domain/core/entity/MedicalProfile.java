/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.core.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import me.kisoft.covid19.domain.entity.DomainEntity;
import me.kisoft.covid19.domain.core.enums.Sex;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

/**
 *
 * @author tareq
 */
@Entity(name = "MedicalProfile")
@Table(name="MEDICAL_PROFILE")
@Getter
@Setter
@Audited
@AuditOverride
public class MedicalProfile extends DomainEntity {


    private int age;
    private double height;
    private double weight;
    private Sex sex;
    @ElementCollection
    private List<String> medications = new ArrayList<>();
    @ElementCollection
    private List<String> medicalFlags = new ArrayList<>();

 @Override
    public String getEntityName() {
        return "medicalProfile";
    }

}
