package br.com.logique.scheduledissues.model.business;

import br.com.logique.scheduledissues.model.domain.ScheduledIssueEntity;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.List;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * Created by gustavo on 19/04/2016.
 */
public class QuartzManager {

    private static final QuartzManager instance = new QuartzManager();
    private JobsScheduled jobsScheduled = new JobsScheduled();

    private Scheduler schedulerCreateIssues;
    private Scheduler schedulerMonitoringIssues;

    public static QuartzManager getInstance() {
        return instance;
    }

    private QuartzManager()  {
        createScheduleCreateIssues();
        createScheduleMonitoringIssues();
    }

    private void createScheduleMonitoringIssues() {
        try {
            schedulerMonitoringIssues =  new StdSchedulerFactory().getScheduler();
            schedulerMonitoringIssues.start();
        } catch (SchedulerException e) {
            System.out.println("Erro fatal o sistema não será iniciado.");
            //TODO logar
        }
    }

    private void createScheduleCreateIssues() {
        try {
            jobsScheduled.removeAll();
            schedulerCreateIssues =  new StdSchedulerFactory().getScheduler();
            schedulerCreateIssues.start();
        } catch (SchedulerException e) {
            System.out.println("Erro fatal o sistema não será iniciado.");
            //TODO logar
        }
    }

    public void scheduleMonitoringJob() throws SchedulerException {
        schedulerMonitoringIssues.scheduleJob(JobBuilder.newJob(SchedulerIssuesJob.class).withIdentity("Monitoring job").build(),
                TriggerBuilder.newTrigger()
                        .withIdentity("issue-monitoring-issues-trigger", "group1")
                        .withSchedule(simpleSchedule().withIntervalInSeconds(10).repeatForever())
                        .build());
    }

    public void scheduleCreateJobs(List<ScheduledIssueEntity> scheduledIssueEntityList){

        for (ScheduledIssueEntity scheduledIssueEntity : scheduledIssueEntityList) {
            try {
                JobDetail job = getCreateJob(scheduledIssueEntity);
                schedulerCreateIssues.scheduleJob(job,
                        getCreateTrigger(scheduledIssueEntity));
                jobsScheduled.put(scheduledIssueEntity, job);
            } catch (SchedulerException e) {
                //TODO logar
            }
        }
    }

    public void removeAllSchedulers() throws SchedulerException {
        schedulerCreateIssues.shutdown(true);
        createScheduleCreateIssues();
    }

    public int jobsScheduled(){
        return jobsScheduled.size();
    }

    public boolean exists(ScheduledIssueEntity issueEntity){
        return jobsScheduled.exists(issueEntity);
    }

    private Trigger getCreateTrigger(ScheduledIssueEntity scheduledIssueEntity) {
        return TriggerBuilder.newTrigger()
                .withIdentity("issue-create-issues-trigger", "group2")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(scheduledIssueEntity.getPeriod()))
                .build();
    }

    private JobDetail getCreateJob(ScheduledIssueEntity scheduledIssueEntity) {
        JobDataMap jobData = new JobDataMap();
        jobData.put("issue", scheduledIssueEntity);
        return JobBuilder.newJob(CreateIssueJob.class)
                .usingJobData(jobData).withIdentity("issue-create-issues-job", "group2").build();
    }

}
