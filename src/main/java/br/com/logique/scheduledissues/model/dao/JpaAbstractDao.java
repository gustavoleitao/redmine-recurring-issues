package br.com.logique.scheduledissues.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Gustavo on 17/04/2016.
 */

public abstract class JpaAbstractDao<T extends GenericEntity> implements Dao<T> {

    protected final Class<T> clazz;
    protected final EntityManagerFactoryHolder entityManagerFactory;


    public JpaAbstractDao() {
        Class<T> clazz = inferirTipoGenerico();
        this.clazz = clazz;
        entityManagerFactory = EntityManagerFactoryHolder.INSTANCE;

    }

    @Override
    public T save(T entidade) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            if (entidade.getId() == null) {
                entityManager.persist(entidade);
            } else {
                entityManager.merge(entidade);
            }
            entityManager.getTransaction().commit();
        } finally {
            close(entityManager);
        }
        return entidade;
    }

    private void close(EntityManager entityManager) {

        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }

        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }

    @Override
    public T findByID(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return (T) entityManager.find(clazz, id);
        } finally {
            close(entityManager);
        }
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<T> all() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("from " + clazz.getName());
            return query.getResultList();
        } finally {
            close(entityManager);
        }
    }

    @Override
    public void remove(T entidade) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (entidade != null) {
                entityManager.remove(entityManager.contains(entidade) ? entidade : entityManager.merge(entidade));
            }
            entityManager.getTransaction().commit();
        } finally {
            close(entityManager);
        }
    }

    @SuppressWarnings("unchecked")
    private Class<T> inferirTipoGenerico() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}