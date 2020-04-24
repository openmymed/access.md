/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.factory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author tareq
 */
public class EntityManagerFactory implements Factory<EntityManager> {

  private static EntityManagerFactory instance = getInstance();
  javax.persistence.EntityManagerFactory emf;

  private EntityManagerFactory() {

  }

  public static EntityManagerFactory getInstance() {
    if (instance == null) {
      instance = new EntityManagerFactory();
    }
    return instance;
  }

  public void setPersistenceUnit(String persistenceUnit) {
    this.emf = Persistence.createEntityManagerFactory(persistenceUnit);
  }

  @Override
  public EntityManager get() {
    return this.emf.createEntityManager();
  }

}
