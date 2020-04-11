/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.auth.service.impl;

import me.kisoft.covid19.domain.auth.entity.SecurityCode;
import me.kisoft.covid19.domain.auth.repo.SecurityCodeRepository;
import me.kisoft.covid19.domain.auth.service.SecurityCodeService;
import me.kisoft.covid19.infra.auth.factory.SecurityCodeRepositoryFactory;

/**
 *
 * @author tareq
 */
public class SecurityCodeServiceImpl implements SecurityCodeService {

    @Override
    public SecurityCode createSecurityCode(long userId) {
        try ( SecurityCodeRepository repo = SecurityCodeRepositoryFactory.getInstance().get()) {
            repo.getValidUserCodes(userId).forEach(code -> {
                code.invalidate();
                repo.save(code);
            });
            SecurityCode sc = new SecurityCode(userId);
            repo.save(sc);
            return sc;
        }
    }

    @Override
    public boolean consumeSecurityCode(String code, long consumerId) {
        try ( SecurityCodeRepository repo = SecurityCodeRepositoryFactory.getInstance().get()) {
            SecurityCode sc = repo.findByCode(code);
            if (sc != null) {
                sc.consume(consumerId);
                repo.save(sc);
                return true;
            }
        }
        return false;
    }

    @Override
    public void expireCodes() {
        try ( SecurityCodeRepository repo = SecurityCodeRepositoryFactory.getInstance().get()) {
            repo.getUnexpiredCodes().forEach(code ->{
                code.timeOut();
                repo.save(code);
            });
        }
    }

}
