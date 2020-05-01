/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.core.service.rest.vo;

import lombok.Getter;
import lombok.Setter;
import org.openmymed.accessmd.domain.core.entity.ICPCCategory;
import org.openmymed.accessmd.domain.core.entity.ICPCEntry;
import org.openmymed.accessmd.domain.core.enums.ICPCType;

/**
 *
 * @author tareq
 */
@Getter
@Setter
public class ICPCEntryVo   {
    private String code;
    private String title;
    private ICPCType type;
    private ICPCCategory category;
    
    public ICPCEntryVo(ICPCEntry entry, String title){
        this.code = entry.getCode();
        this.title = title;
        this.type = entry.getType();
        this.category = entry.getCategory();
    }
}
