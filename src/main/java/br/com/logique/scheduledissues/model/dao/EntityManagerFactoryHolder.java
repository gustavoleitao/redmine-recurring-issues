package br.com.logique.scheduledissues.model.dao;

/**
 * Created by Gustavo on 17/04/2016.
 */

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum EntityManagerFactoryHolder {

    INSTANCE;

    private EntityManagerFactory entityManagerFactory;

    EntityManagerFactoryHolder() {
        entityManagerFactory = Persistence.createEntityManagerFactory("db");
    }

    public EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}