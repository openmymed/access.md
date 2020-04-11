/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.auth.repo.hibernate;

import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import me.kisoft.covid19.domain.auth.entity.SecurityCode;
import me.kisoft.covid19.domain.auth.enums.SecurityCodeStatus;
import me.kisoft.covid19.domain.auth.repo.SecurityCodeRepository;
import me.kisoft.covid19.infra.repo.hiberante.HibernateCrudRepository;

/**
 *
 * @author tareq
 */
public class SecurityCodeRepositoryHibernateImpl extends HibernateCrudRepository<SecurityCode> implements SecurityCodeRepository {

    @Override
    public Class<SecurityCode> getType() {
        return SecurityCode.class;
    }

    @Override
    public SecurityCode findByCode(String code) {
        try {
            return getEm().createNamedQuery("SecurityCode.byCodeAndStatus", SecurityCode.class)
                    .setParameter("code", code)
                    .setParameter("status", SecurityCodeStatus.VALID)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<SecurityCode> getValidUserCodes(long userId) {
        return getEm().createNamedQuery("SecurityCode.byUserAndStatus", SecurityCode.class)
                .setParameter("user_id", userId)
                .setParameter("status", SecurityCodeStatus.VALID)
                .getResultList();
    }

    @Override
    public List<SecurityCode> getUnexpiredCodes() {
        return getEm().createNamedQuery("SecurityCode.validCodesToExpire", SecurityCode.class)
                .setParameter("time", new Date())
                .getResultList();
    }

}
