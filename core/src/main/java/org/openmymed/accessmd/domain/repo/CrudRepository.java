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

    /**
     * Find all types of this entity
     *
     * @return A list of <T>
     */
    List<T> findAll();

    /**
     * Find an entity by Id
     *
     * @param id the id to search for
     * @return an instance of <T> if found, null otherwise
     */
    T findById(Long id);

    /**
     * Save(persist or update) an entity
     *
     * @param toSave the entity to save
     * @return the saved entity
     */
    T save(T toSave);

    /**
     * Delete an entity
     *
     * @param id the id of the entity to delete
     */
    void delete(Long id);

    @Override
    default void close() {

    }
}
