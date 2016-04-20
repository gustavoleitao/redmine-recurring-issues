package br.com.logique.scheduledissues.model.business;

import br.com.logique.scheduledissues.model.domain.ScheduledIssueEntity;
import br.com.logique.scheduledissues.model.service.RedmineService;
import br.com.logique.scheduledissues.model.service.RedmineServiceFactory;
import br.com.logique.scheduledissues.util.ScheduledEntityToRedmineIssue;
import com.taskadapter.redmineapi.RedmineException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by Gustavo on 19/04/2016.
 */
public class CreateIssueJob implements Job{

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScheduledIssueEntity schedule = (ScheduledIssueEntity) jobExecutionContext.getJobDetail()
                .getJobDataMap().get("issue");
        RedmineService redmineService = RedmineServiceFactory.createRedmineService();
//        try {
//            redmineService.createIssue(new ScheduledEntityToRedmineIssue().apply(schedule));
//        } catch (RedmineException e) {
//            e.printStackTrace();
//            //FALHA AO CRIAR TAREFA
//            //TODO logar
//        }
        System.out.println("CHAMOU CREATE");


    }
}
