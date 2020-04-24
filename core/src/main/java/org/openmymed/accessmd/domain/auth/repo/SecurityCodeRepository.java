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
    
    public SecurityCode findByCode(String code);
        
    public List<SecurityCode> getValidUserCodes(long userId);
    
    public List<SecurityCode> getUnexpiredCodes();
    
}
