/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.auth.service.impl;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.openmymed.accessmd.domain.auth.entity.User;
import org.openmymed.accessmd.domain.auth.repo.UserRepository;
import org.openmymed.accessmd.domain.auth.service.UserService;
import org.openmymed.accessmd.infra.auth.factory.UserRepositoryFactory;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author tareq
 */
@Log
public class UserServiceImpl implements UserService {

  @Override
  public User signIn(String username, String password) {
    try (UserRepository repo = UserRepositoryFactory.getInstance().get()) {
      User foundUser = repo.getUserByUsername(username);
      if (foundUser != null) {
        if (StringUtils.equals(password, foundUser.getPassword())) {
          return foundUser;
        }
      }
    }
    return null;
  }

  @Override
  public User signUp(User user) {
    try (UserRepository repo = UserRepositoryFactory.getInstance().get()) {
      if (repo.getUserByUsername(user.getUsername()) == null) {
        return repo.save(user);
      }
    }
    return null;
  }
}
