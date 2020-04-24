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

  public User signIn(String username, String password);

  public User signUp(User user);
}
