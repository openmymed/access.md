/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.auth.repo;

import java.util.List;
import me.kisoft.covid19.domain.auth.entity.SecurityCode;
import me.kisoft.covid19.domain.repo.CrudRepository;

/**
 *
 * @author tareq
 */
public interface SecurityCodeRepository extends CrudRepository<SecurityCode> {
    
    public SecurityCode findByCode(String code);
        
    public List<SecurityCode> getValidUserCodes(long userId);
    
    public List<SecurityCode> getUnexpiredCodes();
    
}
