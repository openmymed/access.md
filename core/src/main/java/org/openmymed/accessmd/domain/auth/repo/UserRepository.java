/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.auth.repo;

import java.util.List;
import org.openmymed.accessmd.domain.auth.entity.User;
import org.openmymed.accessmd.domain.auth.enums.UserRole;
import org.openmymed.accessmd.domain.repo.CrudRepository;

/**
 *
 * @author tareq
 */
public interface UserRepository extends CrudRepository<User> {

    /**
     * Finds a user by their login username
     * @param username the username to search by 
     * @return  the username, if found, null otherwise
     */
  User getUserByUsername(String username);
  
  /**
   * Finds all users belonging to a role
   * @param role the role the users belong to
   * @return a list of users
   */
  List<User> getUsersByRole(UserRole role);
}
