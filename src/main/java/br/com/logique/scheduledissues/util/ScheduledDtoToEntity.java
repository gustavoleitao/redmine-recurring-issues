package br.com.logique.scheduledissues.util;

import br.com.logique.scheduledissues.model.domain.ScheduledIssueEntity;
import br.com.logique.scheduledissues.model.domain.UserBasic;
import br.com.logique.scheduledissues.model.dto.ScheduledIssue;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        entity.setProjectId(scheduledIssue.getProject().getId());
        entity.setTitle(scheduledIssue.getTitle());
        entity.setTrackerId(scheduledIssue.getTracker().getId());
        entity.setUserAssignedId(scheduledIssue.getUserAssigned().getId());
        entity.setWatchersId(getWatcherIds(scheduledIssue.getWatchers()));
        return entity;
    }

    private List<Integer> getWatcherIds(List<UserBasic> watchers) {
        return watchers.stream().map(UserBasic::getId).collect(Collectors.toList());
    }


}
