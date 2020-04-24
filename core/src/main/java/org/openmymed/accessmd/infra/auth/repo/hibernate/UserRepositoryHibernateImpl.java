/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.auth.repo.hibernate;

import java.util.List;
import javax.persistence.NoResultException;
import lombok.SneakyThrows;
import org.openmymed.accessmd.domain.auth.entity.User;
import org.openmymed.accessmd.domain.auth.enums.UserRole;
import org.openmymed.accessmd.domain.auth.repo.UserRepository;
import org.openmymed.accessmd.infra.repo.hiberante.HibernateCrudRepository;

/**
 *
 * @author tareq
 */
public class UserRepositoryHibernateImpl extends HibernateCrudRepository<User> implements UserRepository {

  @SneakyThrows
  @Override
  public User getUserByUsername(String username) {
    try {
      return getEm().createNamedQuery("User.byUsername", User.class)
              .setParameter("username", username)
              .getSingleResult();
    } catch (NoResultException ex) {
      return null;
    }
  }

  @Override
  public Class<User> getType() {
    return User.class;
  }

  @Override
  public List<User> getUsersByRole(UserRole role) {
    return getEm().createNamedQuery("User.listByRole", User.class)
            .setParameter("role", role)
            .getResultList();
  }

}
