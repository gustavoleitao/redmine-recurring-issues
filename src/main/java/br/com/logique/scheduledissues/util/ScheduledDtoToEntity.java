package br.com.logique.scheduledissues.util;

import br.com.logique.scheduledissues.model.domain.ScheduledIssueEntity;
import br.com.logique.scheduledissues.model.dto.ScheduledIssue;

import java.util.function.Function;

/**
 * Created by gustavo on 15/04/2016.
 */
public class ScheduledDtoToEntity implements Function<ScheduledIssue, ScheduledIssueEntity> {

    @Override
    public ScheduledIssueEntity apply(ScheduledIssue scheduledIssue) {
        ScheduledIssueEntity entity = new ScheduledIssueEntity();
        entity.setId(scheduledIssue.getId());
        entity.setDescription(scheduledIssue.getDescription());
        entity.setPeriod(scheduledIssue.getPeriod());
        entity.setProject(scheduledIssue.getProject());
        entity.setTitle(scheduledIssue.getTitle());
        entity.setTracker(scheduledIssue.getTracker());
        entity.setUserAssigned(scheduledIssue.getUserAssigned());
        entity.setWatchers(scheduledIssue.getWatchers());
        return entity;
    }

}
