package br.com.logique.scheduledissues.model.dao;

import br.com.logique.scheduledissues.model.domain.ScheduledIssue;

import java.util.Collection;

/**
 * Created by Gustavo on 14/04/2016.
 */
public interface Dao<T> {

    T save(T entity);

    Collection<T> all();

    T findByID(int id);

    boolean remove(T entity);

    void merge(T entity);
}
