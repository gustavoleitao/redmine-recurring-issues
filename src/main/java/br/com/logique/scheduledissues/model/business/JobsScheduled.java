package br.com.logique.scheduledissues.model.business;

import br.com.logique.scheduledissues.model.domain.ScheduledIssueEntity;
import br.com.logique.scheduledissues.model.dto.ScheduledIssue;
import org.quartz.JobDetail;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Gustavo on 19/04/2016.
 */
public class JobsScheduled {

    private Map<ScheduledIssueEntity, JobDetail> issueJobDetailMap = new ConcurrentHashMap<>();

    private static final JobsScheduled INSTANCE = new JobsScheduled();

    public void put(ScheduledIssueEntity issue, JobDetail jobDetail){
        issueJobDetailMap.put(issue, jobDetail);
    }

    public JobDetail get(ScheduledIssueEntity issue){
        return issueJobDetailMap.get(issue);
    }

    public boolean exists(ScheduledIssueEntity issue){
        return issueJobDetailMap.containsKey(issue);
    }

    public int size(){
        return issueJobDetailMap.size();
    }


    public void removeAll() {
        issueJobDetailMap.clear();
    }
}
