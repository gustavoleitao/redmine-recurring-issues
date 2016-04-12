package br.com.logique.scheduledissues.model.service;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Membership;
import com.taskadapter.redmineapi.bean.Project;
import com.taskadapter.redmineapi.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo on 11/04/2016.
 */
public class RedmineService {

    private String uri;
    private String apiAccessKey;

    private RedmineService(String uri, String apiAccessKey) {
        this.uri = uri;
        this.apiAccessKey = apiAccessKey;
    }

    public List<Issue> getIssues(String projectKey) throws RedmineException {
        Integer queryId = null; // any
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
        List<Issue> issues = mgr.getIssueManager().getIssues(projectKey, queryId);
        return issues;
    }

    public List<Project> getProjects() throws RedmineException {
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
        return mgr.getProjectManager().getProjects();
    }

    public List<User> getUsers() throws RedmineException {
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
        return mgr.getUserManager().getUsers();
    }

    public List<User> getUsersByProject(Integer idProject) throws RedmineException {
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
        List<Membership> members = mgr.getMembershipManager().getMemberships(idProject);
        List<User> users = new ArrayList<>();
        members.stream().forEach(m -> users.add(m.getUser()));
        return users;
    }

    public static class BuilderRedmineService {

        private String uri;
        private String apiAccessKey;

        public BuilderRedmineService withURI(String uri){
            this.uri = uri;
            return this;
        }

        public BuilderRedmineService withApiAccessKey(String apiAccessKey){
            this.apiAccessKey = apiAccessKey;
            return this;
        }

        public RedmineService build(){
            return new RedmineService(uri, apiAccessKey);
        }

    }


}
