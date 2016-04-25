package br.com.logique.scheduledissues.util;

import br.com.logique.scheduledissues.model.domain.*;
import br.com.logique.scheduledissues.model.dto.ScheduledIssue;

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
        scheduledIssue.setProject(scheduledIssueEntity.getProject());
        scheduledIssue.setTracker(scheduledIssueEntity.getTracker());
        scheduledIssue.setUserAssigned(scheduledIssueEntity.getUserAssigned());
        scheduledIssue.setWatchers(scheduledIssueEntity.getWatchers());
        scheduledIssue.setDuration(scheduledIssueEntity.getDurationDays());
        return scheduledIssue;
    }

}
