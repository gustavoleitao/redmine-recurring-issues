package br.com.logique.scheduledissues.model.dao;

import java.util.Collection;

/**
 * Created by Gustavo on 14/04/2016.
 */
public interface Dao<T> {

    T save(T entity);

    Collection<T> all();

    T findByID(Long id);

    void remove(T entity);

}
