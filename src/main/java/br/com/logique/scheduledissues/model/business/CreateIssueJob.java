package br.com.logique.scheduledissues.model.business;

import br.com.logique.scheduledissues.model.domain.ScheduledIssueEntity;
import br.com.logique.scheduledissues.model.service.RedmineService;
import br.com.logique.scheduledissues.model.service.RedmineServiceFactory;
import br.com.logique.scheduledissues.util.ScheduledEntityToRedmineIssue;
import com.taskadapter.redmineapi.RedmineException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Gustavo on 19/04/2016.
 */
public class CreateIssueJob implements Job {

    private Logger logger = LoggerFactory.getLogger(CreateIssueJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScheduledIssueEntity schedule = (ScheduledIssueEntity) jobExecutionContext.getJobDetail()
                .getJobDataMap().get("issue");
        RedmineService redmineService = RedmineServiceFactory.createRedmineService();
        try {
            redmineService.createIssue(new ScheduledEntityToRedmineIssue().apply(schedule));
        } catch (RedmineException e) {
            logger.error("Error to create Issue in redmine server. New attempt in 60 seconds", e);
            sleepMinutes(1);
            JobExecutionException e2 = new JobExecutionException(e);
            e2.initCause(e);
            e2.refireImmediately();
            throw e2;
        }
    }

    private void sleepMinutes(int minutes) {
        try {
            TimeUnit.MINUTES.sleep(minutes);
        } catch (InterruptedException e1) {
            logger.warn("Sleep Interrupted.");
        }
    }
}
