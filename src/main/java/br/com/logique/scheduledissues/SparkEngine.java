package br.com.logique.scheduledissues; /**
 * Created by Gustavo on 10/04/2016.
 */

import br.com.logique.scheduledissues.model.domain.ResponseMsg;
import br.com.logique.scheduledissues.model.domain.ScheduledIssue;
import br.com.logique.scheduledissues.model.service.IssueService;
import br.com.logique.scheduledissues.model.service.RedmineService;
import br.com.logique.scheduledissues.util.JsonUtil;
import com.taskadapter.redmineapi.bean.Project;
import com.taskadapter.redmineapi.bean.Tracker;
import com.taskadapter.redmineapi.bean.User;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.logique.scheduledissues.util.JsonUtil.dataToJson;
import static spark.Spark.*;

public class SparkEngine {

    public void setUp() throws IOException {
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
        Configuration freeMarkerConfiguration = new Configuration(Configuration.getVersion());
        freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(SparkEngine.class, "/templates"));
        freeMarkerEngine.setConfiguration(freeMarkerConfiguration);
        staticFileLocation("/");

        routeIndex(freeMarkerEngine);
        routeUsersByProject();
        routeTrackersByProject();
        routeProjects();
        routeIssues();
        routeRemoveIssue();
        routeSaveIssues();
    }

    private void routeIndex(FreeMarkerEngine freeMarkerEngine) {
        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "issue-form.ftl");
        }, freeMarkerEngine);
    }

    private void routeProjects() {
        get("projects/", (request, response) -> {
            RedmineService redmineService = getRedmineService();
            List<Project> projects = redmineService.getProjects();
            return dataToJson(projects);
        });
    }

    private void routeUsersByProject() {
        get("projects/:id/users/", (request, response) -> {
            String id = request.params(":id");
            RedmineService redmineService = getRedmineService();
            List<User> users = redmineService.getUsersByProject(Integer.valueOf(id));
            return dataToJson(users);
        });
    }

    private void routeTrackersByProject() {
        get("projects/:id/trackers/", (request, response) -> {
            String id = request.params(":id");
            RedmineService redmineService = getRedmineService();
            List<Tracker> trackers = redmineService.getTrackers(Integer.valueOf(id));
            return dataToJson(trackers);
        });
    }

    private void routeIssues() {
        get("issues/", (request, response) -> {
            IssueService issueService = new IssueService();
            return dataToJson(issueService.todos());
        });
    }

    private void routeRemoveIssue() {
        delete("issues/:id", (request, response) -> {
            String idStr = request.params(":id");
            int id = Integer.parseInt(request.params(":id"));
            IssueService issueService = new IssueService();
            if (!issueService.remove(id)) {
                response.status(400);
                return new ResponseMsg("No issue with id '%s' found", idStr);
            }
            return dataToJson(issueService.remove(id));
        });
    }

    private void routeSaveIssues() {
        post("issues/", (request, response) -> {
            IssueService issueService = new IssueService();
            ScheduledIssue scheduledIssue = JsonUtil.jsonToData(request.body(), ScheduledIssue.class);
            if (scheduledIssue.getId() == null) {
                issueService.save(scheduledIssue);
            } else {
                issueService.merge(scheduledIssue);
            }
            return new ResponseMsg("Success to add issue");
        });
    }

    private RedmineService getRedmineService() {
        return new RedmineService.BuilderRedmineService()
                .withApiAccessKey("029b49eaa97e110f0ce5d8149cb4d622365eba58")
                .withURI("http://209.208.90.122/redmine")
                .build();
    }

}
