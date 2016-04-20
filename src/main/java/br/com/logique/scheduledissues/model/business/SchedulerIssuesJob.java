package br.com.logique.scheduledissues.model.business;

import br.com.logique.scheduledissues.model.dao.ScheduledIssuesDao;
import br.com.logique.scheduledissues.model.domain.ScheduledIssueEntity;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * Created by gustavo on 19/04/2016.
 */
public class SchedulerIssuesJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("CHAMOU MONITORING");
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
            e.printStackTrace();
            //TODO logar
        }
    }

}
