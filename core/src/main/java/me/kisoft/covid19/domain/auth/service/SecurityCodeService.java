/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.auth.service;

import me.kisoft.covid19.domain.auth.entity.SecurityCode;

/**
 *
 * @author tareq
 */
public interface SecurityCodeService {
    
    public SecurityCode createSecurityCode(long userId);
    
    public boolean consumeSecurityCode(String code, long consumerId);
    
    public void expireCodes();
}
