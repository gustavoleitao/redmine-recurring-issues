package br.com.logique.scheduledissues.model.business;

import br.com.logique.scheduledissues.model.domain.ScheduledIssueEntity;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * Created by gustavo on 19/04/2016.
 */
public class QuartzManager {

    private static final QuartzManager instance = new QuartzManager();
    private JobsScheduled jobsScheduled = new JobsScheduled();

    private Scheduler schedulerCreateIssues;
    private Scheduler schedulerMonitoringIssues;

    private Logger logger = LoggerFactory.getLogger(SchedulerIssuesJob.class);

    private QuartzManager() {
        createScheduleCreateIssues();
        createScheduleMonitoringIssues();
    }

    public static QuartzManager getInstance() {
        return instance;
    }

    private void createScheduleMonitoringIssues() {
        try {
            schedulerMonitoringIssues = new StdSchedulerFactory().getScheduler();
            schedulerMonitoringIssues.start();
        } catch (SchedulerException e) {
            logger.error("Failed to start create monitoring job.", e);
        }
    }

    private void createScheduleCreateIssues() {
        try {
            schedulerCreateIssues = new StdSchedulerFactory().getScheduler();
            schedulerCreateIssues.start();
        } catch (SchedulerException e) {
            logger.error("Failed to start create issue job.", e);
        }
    }

    public void scheduleMonitoringJob() throws SchedulerException {
        schedulerMonitoringIssues.scheduleJob(JobBuilder.newJob(SchedulerIssuesJob.class).withIdentity("Monitoring job").build(),
                TriggerBuilder.newTrigger()
                        .withIdentity("issue-monitoring-issues-trigger", "group1")
                        .withSchedule(simpleSchedule().withIntervalInSeconds(10).repeatForever())
                        .build());
    }

    public void scheduleCreateJobs(List<ScheduledIssueEntity> scheduledIssueEntityList) {

        for (ScheduledIssueEntity scheduledIssueEntity : scheduledIssueEntityList) {
            try {
                JobDetail job = getCreateJob(scheduledIssueEntity);
                schedulerCreateIssues.scheduleJob(job,
                        getCreateTrigger(scheduledIssueEntity));
                jobsScheduled.put(scheduledIssueEntity, job);
            } catch (SchedulerException e) {
                logger.error("Failed to create job", e);
            }
        }
    }

    public void removeAllSchedulers() throws SchedulerException {

        Set<ScheduledIssueEntity> issues = jobsScheduled.allIssues();
        issues.forEach(i -> {
            try {
                boolean isRemoved = schedulerCreateIssues.deleteJob(jobsScheduled.get(i).getKey());
                logger.debug("Remove result was {} to jobkey {}", isRemoved, jobsScheduled.get(i).getKey());
                jobsScheduled.remove(i);
            } catch (SchedulerException e) {
                logger.error("Failed to remove job {}", i, e);
            }
        });

    }

    public int jobsScheduled() {
        return jobsScheduled.size();
    }

    public boolean exists(ScheduledIssueEntity issueEntity) {
        return jobsScheduled.exists(issueEntity);
    }

    private Trigger getCreateTrigger(ScheduledIssueEntity scheduledIssueEntity) {
        return TriggerBuilder.newTrigger()
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(scheduledIssueEntity.getPeriod()))
                .build();
    }

    private JobDetail getCreateJob(ScheduledIssueEntity scheduledIssueEntity) {
        JobDataMap jobData = new JobDataMap();
        jobData.put("issue", scheduledIssueEntity);
        return JobBuilder.newJob(CreateIssueJob.class)
                .usingJobData(jobData).build();
    }

}
