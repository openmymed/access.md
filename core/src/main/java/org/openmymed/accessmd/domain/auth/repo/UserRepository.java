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

  User getUserByUsername(String username);
  
  List<User> getUsersByRole(UserRole role);
}
