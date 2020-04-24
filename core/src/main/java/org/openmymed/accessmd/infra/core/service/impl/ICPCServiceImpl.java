/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.openmymed.accessmd.domain.core.entity.ICPCEntry;
import org.openmymed.accessmd.domain.core.enums.ICPCType;
import org.openmymed.accessmd.domain.core.service.ICPCService;

/**
 *
 * @author tareq
 */
public class ICPCServiceImpl implements ICPCService {

    private static final Map<String, ICPCEntry> MAP = new HashMap<>();

    @Override
    public List<ICPCEntry> getEntries() {
        return new ArrayList<ICPCEntry>(MAP.values());
    }

    @Override
    public void setEntries(List<ICPCEntry> entries) {
        entries.stream().forEach(entry -> {
            MAP.put(entry.getCode(), entry);
        });
    }

    private List<ICPCEntry> filter(ICPCType type) {
        return MAP.values().stream().filter(entry -> entry.getType() == type).collect(Collectors.toList());
    }

    @Override
    public List<ICPCEntry> getSymptoms() {
        return filter(ICPCType.SYMPTOM);
    }

    @Override
    public List<ICPCEntry> getInfections() {
        return filter(ICPCType.INFECTION);
    }

    @Override
    public List<ICPCEntry> getNeoplasms() {
        return filter(ICPCType.NEOPLASM);
    }

    @Override
    public List<ICPCEntry> getInjuries() {
        return filter(ICPCType.INJURY);
    }
    
    

    @Override
    public List<ICPCEntry> getProcesses() {
        return filter(ICPCType.PROCESS);
    }

    @Override
    public List<ICPCEntry> getCongenitalAnomalies() {
        return filter(ICPCType.CONGENITAL_ANOMALY);
    }

    @Override
    public List<ICPCEntry> getOther() {
        return filter(ICPCType.OTHER);
    }

}
