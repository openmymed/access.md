/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.repo.hiberante;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.TransactionManager;
import lombok.SneakyThrows;
import me.kisoft.covid19.domain.entity.DomainEntity;
import me.kisoft.covid19.domain.repo.CrudRepository;
import me.kisoft.covid19.infra.factory.EntityManagerFactory;

/**
 *
 * @author tareq
 */
public abstract class HibernateCrudRepository<T extends DomainEntity> implements CrudRepository<T>, AutoCloseable {

  private EntityManager em = EntityManagerFactory.getInstance().get();

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

    return null;
  }

  @SneakyThrows
  @Override
  public T save(T toSave) {
   return null;
  }

  @SneakyThrows
  @Override
  public void delete(Long id) {


  }

  @Override
  public void close() {
    getEm().close();
  }

  public TransactionManager getTransactionManager() {
    return com.arjuna.ats.jta.TransactionManager.transactionManager();
  }

  public abstract Class<T> getType();

}
