/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.repo;

import java.util.List;
import me.kisoft.covid19.domain.entity.DomainEntity;

/**
 *
 * @author tareq
 */
public interface CrudRepository<T extends DomainEntity> extends AutoCloseable {

    List<T> findAll();

    T findById(Long id);

    default T refresh(T toRefresh) {
        //toRefresh = findById(toRefresh.getId());
        return toRefresh;
    }

    T save(T toSave);

    public default T update(T toUpdate) {
        //return this.update(toUpdate, toUpdate.getId());
        return null;
    }

    void delete(Long id);
}
