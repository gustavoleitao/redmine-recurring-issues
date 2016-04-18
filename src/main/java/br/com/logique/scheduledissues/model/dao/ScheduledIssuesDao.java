package br.com.logique.scheduledissues.model.dao;

import br.com.logique.scheduledissues.model.domain.ProjectBasic;
import br.com.logique.scheduledissues.model.domain.ScheduledIssueEntity;
import br.com.logique.scheduledissues.model.domain.TrackerBasic;
import br.com.logique.scheduledissues.model.domain.UserBasic;

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

        if (entidade.getProject() != null) {
            ProjectDao projectDao = new ProjectDao();
            ProjectBasic project = projectDao.findByID(entidade.getProject().getId());
            if (project != null) {
                entidade.setProject(project);
            }
        }

        UserDao userDao = new UserDao();
        if (entidade.getUserAssigned() != null){
            UserBasic user = userDao.findByID(entidade.getUserAssigned().getId());
            if (user != null) {
                entidade.setUserAssigned(user);
            }
        }

        List<UserBasic> watchers = entidade.getWatchers();
        if (watchers != null){
            for (UserBasic watcher : watchers) {
                UserBasic watcherUser = userDao.findByID(watcher.getId());
                List<UserBasic> userBasics = new ArrayList<>();
                if (watcherUser != null) {
                    userBasics.add(watcherUser);
                } else {
                    userBasics.add(watcher);
                }
            }
        }

        if (entidade.getTracker() != null){
            TrackerDao trackerDao = new TrackerDao();
            TrackerBasic tracker = trackerDao.findByID(entidade.getTracker().getId());
            if (tracker != null) {
                entidade.setTracker(tracker);
            }
        }

        return super.save(entidade);
    }
}
