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
        return new ScheduledIssueEntity();
    }


}
