/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.auth.service;

import me.kisoft.covid19.domain.auth.entity.User;

/**
 *
 * @author tareq
 */
public interface UserService {

  public User signIn(String username, String password);

  public User signUp(User user);
}
