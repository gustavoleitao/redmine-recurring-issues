package br.com.logique.scheduledissues.model.business;

import br.com.logique.scheduledissues.model.dao.ScheduledIssuesDao;
import br.com.logique.scheduledissues.model.domain.ScheduledIssueEntity;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by gustavo on 19/04/2016.
 */
public class SchedulerIssuesJob implements Job {

    private Logger logger = LoggerFactory.getLogger(SchedulerIssuesJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            monitorinSchedulers();
        } catch (Throwable e) {
            logger.error("Intercepted exception. Error to run the monitoring job.", e);
        }
    }

    private void monitorinSchedulers() {
        logger.trace("Monitoring schedulers changes");
        ScheduledIssuesDao scheduledIssuesDao = new ScheduledIssuesDao();
        List<ScheduledIssueEntity> issues = scheduledIssuesDao.all();

        QuartzManager quartzManager = QuartzManager.getInstance();
        if (isSchedulerChanged(issues)) {
            tryRemoveAllSchedulers(quartzManager);
            quartzManager.scheduleCreateJobs(issues);
        }
    }

    private boolean isSchedulerChanged(List<ScheduledIssueEntity> issues) {
        QuartzManager quartzManager = QuartzManager.getInstance();
        boolean c1 = issues.size() != quartzManager.jobsScheduled();
        boolean c2 = false;
        if (!c1) {
            for (ScheduledIssueEntity issue : issues) {
                if (!quartzManager.exists(issue)) {
                    c2 = true;
                    break;
                }
            }
        }
        return c1 || c2;
    }

    private void tryRemoveAllSchedulers(QuartzManager quartzManager) {
        try {
            quartzManager.removeAllSchedulers();
        } catch (SchedulerException e) {
            logger.error("Error ro remove all schedulers.", e);
        }
    }

}
