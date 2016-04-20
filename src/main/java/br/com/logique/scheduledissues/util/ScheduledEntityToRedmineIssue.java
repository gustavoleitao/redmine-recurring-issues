package br.com.logique.scheduledissues.util;

import br.com.logique.scheduledissues.model.domain.ScheduledIssueEntity;
import br.com.logique.scheduledissues.model.domain.UserBasic;
import com.taskadapter.redmineapi.bean.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Gustavo on 19/04/2016.
 */
public class ScheduledEntityToRedmineIssue implements Function<ScheduledIssueEntity, Issue> {

    @Override
    public Issue apply(ScheduledIssueEntity scheduledIssueEntity) {

        int idProject = Math.toIntExact(scheduledIssueEntity.getProject().getId());
        int idUserAssigned = Math.toIntExact(scheduledIssueEntity.getUserAssigned().getId());
        int idTracker = Math.toIntExact(scheduledIssueEntity.getTracker().getId());

        Collection<Watcher> watchers = new ArrayList<>();
        List<UserBasic> watchersUsers = scheduledIssueEntity.getWatchers();
        for (UserBasic watchersUser : watchersUsers) {
            watchers.add(WatcherFactory.create(Math.toIntExact(watchersUser.getId())));
        }

        Issue issue = IssueFactory.create(idProject, scheduledIssueEntity.getTitle());
        issue.setDescription(scheduledIssueEntity.getDescription());
        issue.setCreatedOn(new Date());
        issue.setStartDate(new Date());
        issue.setTracker(TrackerFactory.create(idTracker));
        issue.addWatchers(watchers);
        issue.setAssignee(UserFactory.create(idUserAssigned));

        return issue;
    }

}
