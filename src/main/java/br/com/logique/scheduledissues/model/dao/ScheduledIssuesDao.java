package br.com.logique.scheduledissues.model.dao;

import br.com.logique.scheduledissues.model.domain.ProjectBasic;
import br.com.logique.scheduledissues.model.domain.ScheduledIssueEntity;
import br.com.logique.scheduledissues.model.domain.TrackerBasic;
import br.com.logique.scheduledissues.model.domain.UserBasic;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Gustavo on 14/04/2016.
 */
public class ScheduledIssuesDao extends JpaAbstractDao<ScheduledIssueEntity> {


    public ScheduledIssuesDao() {
        super();
    }

    @Override
    public ScheduledIssueEntity save(ScheduledIssueEntity entidade) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            entityManager.getTransaction().begin();

            entidade.setProject(saveProject(entityManager, entidade.getProject()));
            entidade.setUserAssigned(saveUser(entityManager, entidade.getUserAssigned()));
            entidade.setWatchers(saveWatchers(entityManager, entidade.getWatchers()));
            entidade.setTracker(saveTracker(entityManager, entidade.getTracker()));

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

    private TrackerBasic saveTracker(EntityManager entitiManager, TrackerBasic entity) {
        TrackerDao trackerDao = new TrackerDao();
        if (entity != null) {
            TrackerBasic managerEntity = trackerDao.findByID(entity.getId());
            if (managerEntity == null) {
                entitiManager.persist(entity);
            }else{
                entity = managerEntity;
            }
        }
        return entity;
    }

    private List<UserBasic> saveWatchers(EntityManager entitiManager, Collection<UserBasic> watchers) {
        List<UserBasic> users = new ArrayList<>();
        UserDao userDao = new UserDao();
        if (watchers != null) {
            for (UserBasic watcher : watchers) {
                UserBasic managerEntity = userDao.findByID(watcher.getId());
                if (managerEntity == null) {
                    entitiManager.persist(watcher);
                }else{
                    watcher = managerEntity;
                }
                users.add(watcher);
            }
        }
        return users;
    }

    private UserBasic saveUser(EntityManager entitiManager, UserBasic entity) {
        UserDao userDao = new UserDao();
        if (entity != null) {
            UserBasic managerEntity = userDao.findByID(entity.getId());
            if (managerEntity == null) {
                entitiManager.persist(entity);
            }else{
                entity = managerEntity;
            }
        }
        return entity;
    }

    private ProjectBasic saveProject(EntityManager entitiManager, ProjectBasic entity) {
        if (entity != null) {
            ProjectDao projectDao = new ProjectDao();
            ProjectBasic managerEntity = projectDao.findByID(entity.getId());
            if (managerEntity == null) {
                entitiManager.persist(entity);
            }else{
                entity = managerEntity;
            }
        }
        return entity;
    }
}
