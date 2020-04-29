/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.auth.repo;

import java.util.List;
import org.openmymed.accessmd.domain.auth.entity.SecurityCode;
import org.openmymed.accessmd.domain.repo.CrudRepository;

/**
 *
 * @author tareq
 */
public interface SecurityCodeRepository extends CrudRepository<SecurityCode> {
    
    /**
     * Finds the security code by the provided client code
     * @param clientCode the client code to search by
     * @return  the security code, if found, null otherwise
     */
    public SecurityCode findByCode(String clientCode);
        
    /**
     * Gets all valid codes for a certain user
     * @param userId the id of the user to get valid codes for
     * @return  a list of codes
     */
    public List<SecurityCode> getValidUserCodes(long userId);
    
    /**
     * Gets a list of all unexpired codes so far
     * @return a list of unexpired code
     */
    public List<SecurityCode> getUnexpiredCodes();
    
}
