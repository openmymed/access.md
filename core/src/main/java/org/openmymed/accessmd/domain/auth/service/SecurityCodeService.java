/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.auth.service;

import org.openmymed.accessmd.domain.auth.entity.SecurityCode;

/**
 *
 * @author tareq
 */
public interface SecurityCodeService {
    
    /**
     * Creates a new security code for a user
     * @param userId the id of the user to create the code for
     * @return  the created code
     */
    public SecurityCode createSecurityCode(long userId);
    
    /**
     * Consumes a security code by searching for the client code
     * @param clientCode the clientCode for the securityCode
     * @param consumerId the Id of the consuming user
     * @return the userId of the user the code belongs to if found and consumed successfully, -1 otherwise
     */
    public long consumeSecurityCode(String clientCode, long consumerId);
    
    /**
     * Expires all codes past the expiry date
     */
    public void expireCodes();
}
