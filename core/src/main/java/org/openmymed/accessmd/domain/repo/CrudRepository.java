/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.repo;

import java.util.List;
import org.openmymed.accessmd.domain.entity.DomainEntity;

/**
 *
 * @author tareq
 */
public interface CrudRepository<T extends DomainEntity> extends AutoCloseable {

    List<T> findAll();

    T findById(Long id);


    T save(T toSave);


    void delete(Long id);
    
    @Override
    default void close(){
        
    }
}
