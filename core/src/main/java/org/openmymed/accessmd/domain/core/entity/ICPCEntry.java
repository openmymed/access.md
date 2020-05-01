/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.openmymed.accessmd.domain.core.enums.ICPCType;

/**
 *
 * @author tareq
 */
@Getter
@Setter
@NoArgsConstructor
public class ICPCEntry {

    private String code;
    private ICPCType type;

    public ICPCEntry(String code, ICPCType type) {
        this.code = code;
        this.type = type;
    }

    @Transient
    public ICPCCategory getCategory() {
        if (code != null) {
            return ICPCCategory.of(code.charAt(0));
        }
        return null;
    }
}
