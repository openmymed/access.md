/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.auth.service;

import org.openmymed.accessmd.domain.auth.entity.User;

/**
 *
 * @author tareq
 */
public interface UserService {

  /**
   * Signs in a user using their username and password
   * @param username the username to sign in with
   * @param password the password to match
   * @return  the user, if the login succeeds, null otherwise.
   */
  public User signIn(String username, String password);

  /**
   * Adds a new user account
   * @param user the user to add
   * @return the created user
   */
  public User signUp(User user);
}
