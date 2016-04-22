package br.com.logique.scheduledissues.model.business;

import br.com.logique.scheduledissues.model.domain.ScheduledIssueEntity;
import org.quartz.JobDetail;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Gustavo on 19/04/2016.
 */
public class JobsScheduled {

    private static final JobsScheduled INSTANCE = new JobsScheduled();
    private Map<ScheduledIssueEntity, JobDetail> issueJobDetailMap = new ConcurrentHashMap<>();

    public void put(ScheduledIssueEntity issue, JobDetail jobDetail) {
        issueJobDetailMap.put(issue, jobDetail);
    }

    public JobDetail get(ScheduledIssueEntity issue) {
        return issueJobDetailMap.get(issue);
    }

    public boolean exists(ScheduledIssueEntity issue) {
        return issueJobDetailMap.containsKey(issue);
    }

    public int size() {
        return issueJobDetailMap.size();
    }

    public void removeAll() {
        issueJobDetailMap.clear();
    }

    public void remove(ScheduledIssueEntity issueEntity) {
        issueJobDetailMap.remove(issueEntity);
    }

    public Set<ScheduledIssueEntity> allIssues() {
        return issueJobDetailMap.keySet();
    }

    public Collection<JobDetail> allJobs() {
        return issueJobDetailMap.values();
    }
}
