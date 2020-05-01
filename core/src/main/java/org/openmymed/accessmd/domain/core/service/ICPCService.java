/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.core.service;

import java.util.List;
import org.openmymed.accessmd.domain.core.entity.ICPCEntry;

/**
 *
 * @author tareq
 */
public interface ICPCService {
    
    /**
     * Get a list of ICPC Symptom codes
     * @return a list of ICPC Codes
     */
    List<ICPCEntry> getSymptoms();
    
      /**
     * Get a list of ICPC Infection codes
     * @return a list of ICPC Codes
     */
    List<ICPCEntry> getInfections();
    
      /**
     * Get a list of ICPC Neoplasm codes
     * @return a list of ICPC Codes
     */
    List<ICPCEntry> getNeoplasms();
      /**
     * Get a list of ICPC Injury codes
     * @return a list of ICPC Codes
     */
    List<ICPCEntry> getInjuries();
    
      /**
     * Get a list of ICPC Process codes
     * @return a list of ICPC Codes
     */
    List<ICPCEntry> getProcesses();
    
      /**
     * Get a list of ICPC Congenital Anomaly codes
     * @return a list of ICPC Codes
     */
    List<ICPCEntry> getCongenitalAnomalies();
    
      /**
     * Get a list of ICPC All codes
     * @return a list of ICPC Codes
     */
    List<ICPCEntry> getEntries();
    
      /**
     * Get a list of ICPC Other codes
     * @return a list of ICPC Codes
     */
    List<ICPCEntry> getOther();
    
    /**
     * Set a the list of ICPC Entries
     * @param entries a new list of ICPC entries.
     */
    void setEntries(List<ICPCEntry> entries);
    
    
    /**
     * Gets an entry by its ICPC Code
     * @param code the code of the entry
     * @return  the entry, if found, null otherwise
     */
    ICPCEntry getEntryByCode(String code);
    
    
}
