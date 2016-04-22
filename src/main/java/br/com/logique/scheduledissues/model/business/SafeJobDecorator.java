package br.com.logique.scheduledissues.model.business;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Gustavo on 21/04/2016.
 */
public class SafeJobDecorator implements Job {

    private Logger logger = LoggerFactory.getLogger(SchedulerIssuesJob.class);

    private Job job;

    private SafeJobDecorator(Job job) {
        this.job = job;
    }

    public static Job of(Job job) {
        return new SafeJobDecorator(job);
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            job.execute(context);
        } catch (Throwable e) {
            logger.error("Intercepted exception. Error to run the job.", e);
        }
    }
}
