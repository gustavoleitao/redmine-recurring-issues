package br.com.logique.scheduledissues.model.service;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.*;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<Issue> getIssues(String projectKey) {
        Integer queryId = null; // any
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
        List<Issue> issues = null;
        try {
            issues = mgr.getIssueManager().getIssues(projectKey, queryId);
        } catch (RedmineException e) {
            e.printStackTrace();
        }
        return issues;
    }

    public List<Project> getProjects() {
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
        try {
            return mgr.getProjectManager().getProjects();
        } catch (RedmineException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<User> getUsers() {
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
        try {
            return mgr.getUserManager().getUsers();
        } catch (RedmineException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<User> getUsersByProject(Integer idProject) {
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
        List<Membership> members = null;
        try {
            members = mgr.getMembershipManager().getMemberships(idProject);
        } catch (RedmineException e) {
            e.printStackTrace();
        }
        List<User> users = new ArrayList<>();
        members.stream().forEach(m -> users.add(m.getUser()));
        return users;
    }

    public List<Tracker> getTrackers(Integer idProject) {
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
        try {
            return new ArrayList<>(mgr.getProjectManager().getProjectById(idProject).getTrackers());
        } catch (RedmineException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Project getProjectById(Integer projectId) throws RedmineException {
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
        return mgr.getProjectManager().getProjectById(projectId);
    }

    public Tracker getTrackerById(Integer trackerId) throws RedmineException {
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
        List<Tracker> trackers = mgr.getIssueManager().getTrackers();
        return trackers.stream().filter(x -> x.getId() == trackerId).findAny().get();
    }

    public User getUserById(Integer userAssignedId) throws RedmineException {
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
        return mgr.getUserManager().getUserById(userAssignedId);
    }

    public static class BuilderRedmineService {

        private String uri;
        private String apiAccessKey;

        public BuilderRedmineService withURI(String uri) {
            this.uri = uri;
            return this;
        }

        public BuilderRedmineService withApiAccessKey(String apiAccessKey) {
            this.apiAccessKey = apiAccessKey;
            return this;
        }

        public RedmineService build() {
            return new RedmineService(uri, apiAccessKey);
        }

    }


}
