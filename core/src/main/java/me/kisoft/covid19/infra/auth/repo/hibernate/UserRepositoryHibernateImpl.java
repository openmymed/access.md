/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.auth.repo.hibernate;

import java.util.List;
import javax.persistence.NoResultException;
import lombok.SneakyThrows;
import me.kisoft.covid19.domain.auth.entity.User;
import me.kisoft.covid19.domain.auth.enums.UserRole;
import me.kisoft.covid19.domain.auth.repo.UserRepository;
import me.kisoft.covid19.infra.repo.hiberante.HibernateCrudRepository;

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
