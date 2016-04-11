package br.com.logique.scheduledissues.model.service;


import com.taskadapter.redmineapi.bean.Project;
import org.junit.Test;

import java.util.List;

/**
 * Created by Gustavo on 11/04/2016.
 */
public class RedmineServiceTest {

    @Test
    public void testGetIssues() throws Exception {
        RedmineService redmineService = new RedmineService.BuilderRedmineService()
                .withApiAccessKey("029b49eaa97e110f0ce5d8149cb4d622365eba58")
                .withURI("http://209.208.90.122/redmine")
                .build();
        redmineService.getIssues("administracao-logique");
    }

    @Test
    public void testGetProjects() throws Exception {
        RedmineService redmineService = new RedmineService.BuilderRedmineService()
                .withApiAccessKey("029b49eaa97e110f0ce5d8149cb4d622365eba58")
                .withURI("http://209.208.90.122/redmine")
                .build();
        List<Project> projects = redmineService.getProjects();
        for (Project project : projects) {
            System.out.println(project);
        }

    }

}