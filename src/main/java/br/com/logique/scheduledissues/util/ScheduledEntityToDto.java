package br.com.logique.scheduledissues.util;

import br.com.logique.scheduledissues.model.domain.*;
import br.com.logique.scheduledissues.model.dto.ScheduledIssue;
import br.com.logique.scheduledissues.model.service.RedmineService;
import br.com.logique.scheduledissues.model.service.RedmineServiceFactory;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by gustavo on 15/04/2016.
 */
public class ScheduledEntityToDto implements Function<ScheduledIssueEntity, ScheduledIssue> {

    @Override
    public ScheduledIssue apply(ScheduledIssueEntity scheduledIssueEntity) {
        ScheduledIssue scheduledIssue = new ScheduledIssue();
        scheduledIssue.setTitle(scheduledIssueEntity.getTitle());
        scheduledIssue.setPeriod(scheduledIssueEntity.getPeriod());
        scheduledIssue.setDescription(scheduledIssueEntity.getDescription());
        scheduledIssue.setId(scheduledIssueEntity.getId());
        scheduledIssue.setId(scheduledIssueEntity.getId());
        scheduledIssue.setProject(getProjectById(scheduledIssueEntity.getProjectId()));
        scheduledIssue.setTracker(getTrackerById(scheduledIssueEntity.getTrackerId()));
        scheduledIssue.setUserAssigned(getUserById(scheduledIssueEntity.getUserAssignedId()));
        scheduledIssue.setWatchers(getWatchersByIds(scheduledIssueEntity.getWatchersId()));
        return scheduledIssue;
    }

    private List<UserBasic> getWatchersByIds(List<Integer> watchersId) {
        RedmineService redmineService = RedmineServiceFactory.createRedmineService();
        List<UserBasic> userBasics = new ArrayList<>();

        watchersId.stream().forEach(w -> {
            try {
                userBasics.add(toUserBasic(redmineService.getUserById(w)));
            } catch (RedmineException e) {
                throw new RedmineRuntimeException(e);
            }
        });

        return userBasics;
    }

    private UserBasic getUserById(Integer userAssignedId) {
        RedmineService redmineService = RedmineServiceFactory.createRedmineService();
        try {
            User user = redmineService.getUserById(userAssignedId);
            return toUserBasic(user);
        } catch (RedmineException e) {
            throw new RedmineRuntimeException(e);
        }
    }

    private TrackerBasic getTrackerById(Integer trackerId) {
        RedmineService redmineService = RedmineServiceFactory.createRedmineService();
        try {
            return toTrackerBasic(redmineService.getTrackerById(trackerId));
        } catch (RedmineException e) {
            throw new RedmineRuntimeException(e);
        }
    }

    private ProjectBasic getProjectById(Integer projectId) {
        RedmineService redmineService = RedmineServiceFactory.createRedmineService();
        try {
            return toProjectBasic(redmineService.getProjectById(projectId));
        } catch (RedmineException e) {
            throw new RedmineRuntimeException(e);
        }
    }

    private UserBasic toUserBasic(User user) {
        UserBasic userBasic = new UserBasic();
        userBasic.setId(user.getId());
        userBasic.setFullName(user.getFullName());
        return userBasic;
    }

    private TrackerBasic toTrackerBasic(com.taskadapter.redmineapi.bean.Tracker tracker) {
        TrackerBasic trackerBasicBasic = new TrackerBasic();
        trackerBasicBasic.setId(tracker.getId());
        trackerBasicBasic.setName(tracker.getName());
        return trackerBasicBasic;
    }


    private ProjectBasic toProjectBasic(com.taskadapter.redmineapi.bean.Project projectById) {
        ProjectBasic projectBasic = new ProjectBasic();
        projectBasic.setId(projectById.getId());
        projectBasic.setName(projectById.getName());
        return projectBasic;
    }

}
