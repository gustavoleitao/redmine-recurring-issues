package br.com.logique.scheduledissues.model.dao;

import br.com.logique.scheduledissues.model.domain.ProjectBasic;
import br.com.logique.scheduledissues.model.domain.ScheduledIssueEntity;
import br.com.logique.scheduledissues.model.domain.TrackerBasic;
import br.com.logique.scheduledissues.model.domain.UserBasic;

import javax.persistence.EntityManager;
import java.util.ArrayList;
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

            processProject(entidade, entityManager);
            UserBasic userAssigned = processUserAssigned(entidade, entityManager);
            processWatchers(entidade, entityManager, userAssigned);
            processTracker(entidade, entityManager);

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

    private void processTracker(ScheduledIssueEntity entidade, EntityManager entityManager) {
        TrackerBasic tracker = getTracker(entidade.getTracker());
        if (tracker == null && entidade.getTracker() != null) {
            entityManager.persist(entidade.getTracker());
            tracker = entidade.getTracker();
        }
        entidade.setTracker(tracker);
    }

    private void processWatchers(ScheduledIssueEntity entidade, EntityManager entityManager, UserBasic userAssigned) {
        List<UserBasic> watchersToSave = new ArrayList<>();
        List<UserBasic> watchers = entidade.getWatchers();
        if (watchers != null) {
            for (UserBasic watcher : watchers) {
                UserBasic dbWatcher = getUser(watcher);
                if (watcher.getId() == userAssigned.getId()) {
                    watchersToSave.add(userAssigned);
                } else {
                    if (dbWatcher == null && watcher != null) {
                        entityManager.persist(watcher);
                        watchersToSave.add(watcher);
                    } else {
                        watchersToSave.add(dbWatcher);
                    }
                }

            }
            entidade.setWatchers(watchersToSave);
        }
    }

    private UserBasic processUserAssigned(ScheduledIssueEntity entidade, EntityManager entityManager) {
        UserBasic userAssigned = getUser(entidade.getUserAssigned());
        if (userAssigned == null && entidade.getUserAssigned() != null) {
            entityManager.persist(entidade.getUserAssigned());
            userAssigned = entidade.getUserAssigned();
        }
        entidade.setUserAssigned(userAssigned);
        return userAssigned;
    }

    private void processProject(ScheduledIssueEntity entidade, EntityManager entityManager) {
        ProjectBasic project = getProject(entidade.getProject());
        if (project == null && entidade.getProject() != null) {
            entityManager.persist(entidade.getProject());
            project = entidade.getProject();
        }
        entidade.setProject(project);
    }

    private TrackerBasic getTracker(TrackerBasic entity) {
        TrackerDao trackerDao = new TrackerDao();
        TrackerBasic trackerBasic = null;
        if (entity != null) {
            TrackerBasic managerEntity = trackerDao.findByID(entity.getId());
            if (managerEntity != null) {
                trackerBasic = managerEntity;
            }
        }
        return trackerBasic;
    }


    private UserBasic getUser(UserBasic entity) {
        UserDao userDao = new UserDao();
        UserBasic userBasic = null;
        if (entity != null) {
            UserBasic managerEntity = userDao.findByID(entity.getId());
            if (managerEntity != null) {
                userBasic = managerEntity;
            }
        }
        return userBasic;
    }

    private ProjectBasic getProject(ProjectBasic entity) {
        ProjectBasic projectBasic = null;
        if (entity != null) {
            ProjectDao projectDao = new ProjectDao();
            ProjectBasic managerEntity = projectDao.findByID(entity.getId());
            if (managerEntity != null) {
                projectBasic = managerEntity;
            }
        }
        return projectBasic;
    }
}
