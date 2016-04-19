package br.com.logique.scheduledissues.model.business;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by gustavo on 19/04/2016.
 */
public class QuartzManager {

    public void startJobs() throws SchedulerException {
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerName", "group1")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(5).repeatForever())
                .build();

        Trigger trigger2 = TriggerBuilder.newTrigger()
                .withIdentity("dummyTriggerName", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();

        JobDetail job = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("dummyJobName", "group1").build();

        scheduler.scheduleJob(job, trigger2);

    }

    public static void main(String[] args) throws SchedulerException {
        QuartzManager quartzManager = new QuartzManager();
        quartzManager.startJobs();
    }

}
