/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.kisoft.covid19.domain.core.entity.ICPCEntry;
import me.kisoft.covid19.domain.core.service.ICPCService;

/**
 *
 * @author tareq
 */
public class ICPCServiceImpl implements ICPCService{

    private static final Map<String,ICPCEntry> MAP = new HashMap<>();
    @Override
    public List<ICPCEntry> getEntries() {
        return new ArrayList<ICPCEntry>(MAP.values());
    }

    @Override
    public void setEntries(List<ICPCEntry> entries) {
        entries.stream().forEach(entry->{
            MAP.put(entry.getCode(), entry);
        });
    }
    
}
