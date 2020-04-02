/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.auth.service.impl;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import me.kisoft.covid19.domain.auth.entity.User;
import me.kisoft.covid19.domain.auth.repo.UserRepository;
import me.kisoft.covid19.domain.auth.service.UserService;
import me.kisoft.covid19.infra.auth.factory.UserRepositoryFactory;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author tareq
 */
@Log
public class UserServiceImpl implements UserService {

  @SneakyThrows
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

  @SneakyThrows
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
