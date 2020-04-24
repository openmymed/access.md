/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.repo.hiberante;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.TransactionManager;
import lombok.SneakyThrows;
import org.openmymed.accessmd.domain.entity.DomainEntity;
import org.openmymed.accessmd.domain.repo.CrudRepository;
import org.openmymed.accessmd.infra.factory.EntityManagerFactory;

/**
 *
 * @author tareq
 */
public abstract class HibernateCrudRepository<T extends DomainEntity> implements CrudRepository<T>, AutoCloseable {

    private static final TransactionManager MANAGER = com.arjuna.ats.jta.TransactionManager.transactionManager();
    private EntityManager em = EntityManagerFactory.getInstance().get();

    public HibernateCrudRepository() {

    }

    protected EntityManager getEm() {
        return em;
    }

    @Override
    public List<T> findAll() {
        List<T> entities = new ArrayList<>();
        return entities;
    }

    @Override
    public T findById(Long id) {
        return getEm().find(getType(), id);
    }

    @SneakyThrows
    @Override
    public T save(T toSave) {
        startTransaction();
        try {
            if (toSave.getId() != null) {
                toSave = getEm().merge(toSave);
            } else {
                getEm().persist(toSave);
            }
        } finally {
            commitTransaction();
        }
        return toSave;
    }

    @SneakyThrows
    @Override
    public void delete(Long id) {
        T toDelete = findById(id);
        if (toDelete != null) {
            startTransaction();
            try {
                getEm().remove(toDelete);
            } finally {
                commitTransaction();
            }
        }

    }

    @Override
    public void close() {
        getEm().close();
    }

    public TransactionManager getTransactionManager() {
        return MANAGER;
    }

    @SneakyThrows
    public void startTransaction() {
        getTransactionManager().begin();
        getEm().joinTransaction();
    }

    @SneakyThrows
    public void commitTransaction() {
        getTransactionManager().commit();
    }

    public abstract Class<T> getType();

}
