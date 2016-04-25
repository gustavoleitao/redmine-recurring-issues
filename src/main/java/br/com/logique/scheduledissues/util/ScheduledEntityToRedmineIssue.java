package br.com.logique.scheduledissues.util;

import br.com.logique.scheduledissues.model.business.Application;
import br.com.logique.scheduledissues.model.domain.ScheduledIssueEntity;
import br.com.logique.scheduledissues.model.domain.UserBasic;
import com.taskadapter.redmineapi.bean.*;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.*;
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

        Issue issue = IssueFactory.create(idProject, parseMsg(scheduledIssueEntity.getTitle()));
        issue.setDescription(parseMsg(scheduledIssueEntity.getDescription()));
        issue.setCreatedOn(new Date());
        issue.setStartDate(new Date());
        issue.setDueDate(getDueDate(scheduledIssueEntity.getDurationDays()));
        issue.setTracker(TrackerFactory.create(idTracker));
        issue.addWatchers(watchers);
        issue.setAssignee(UserFactory.create(idUserAssigned));

        return issue;
    }

    private String parseMsg(String message) {
        Clock clock = Application.getInstance().getClock();
        Locale locale = Application.getInstance().getLocale();

        LocalDateTime now = LocalDateTime.now(clock);

        String currentMonth = now.getMonth().getDisplayName(TextStyle.FULL, locale);
        String currentDay = String.valueOf(now.getDayOfMonth());
        String currentYear =  String.valueOf(now.getYear());
        String weekDay = now.getDayOfWeek().getDisplayName(TextStyle.FULL, locale);


        message = message.replaceAll("#month",currentMonth);
        message = message.replaceAll("#day",currentDay);
        message = message.replaceAll("#year",currentYear);
        message = message.replaceAll("#weekDay", weekDay);

        return message;
    }

    private Date getDueDate(Integer duration) {
        LocalDateTime dueLocalDate = LocalDateTime.now().plusDays(duration);
        return Date.from(dueLocalDate.atZone(ZoneId.systemDefault()).toInstant());
    }

}
