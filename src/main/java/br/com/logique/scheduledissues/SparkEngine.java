package br.com.logique.scheduledissues; /**
 * Created by Gustavo on 10/04/2016.
 */

import br.com.logique.scheduledissues.model.domain.ResponseMsg;
import br.com.logique.scheduledissues.model.dto.FieldValidate;
import br.com.logique.scheduledissues.model.dto.ScheduledIssue;
import br.com.logique.scheduledissues.model.dto.ValidateResponse;
import br.com.logique.scheduledissues.model.service.IssueService;
import br.com.logique.scheduledissues.model.service.RedmineService;
import br.com.logique.scheduledissues.model.service.RedmineServiceFactory;
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

    private static String port = System.getProperty("app.port", "4567");

    public void setUp() throws IOException {
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
        Configuration freeMarkerConfiguration = new Configuration(Configuration.getVersion());
        freeMarkerConfiguration.setOutputEncoding("UTF-8");
        freeMarkerConfiguration.setDefaultEncoding("UTF-8");
        freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(SparkEngine.class, "/public/templates"));
        freeMarkerEngine.setConfiguration(freeMarkerConfiguration);
        staticFileLocation("/public");
        port(Integer.parseInt(port));
        routes(freeMarkerEngine);
    }

    private void routes(FreeMarkerEngine freeMarkerEngine) {
        routeIndex(freeMarkerEngine);
        routeUsersByProject();
        routeTrackersByProject();
        routeProjects();
        routeIssues();
        routeRemoveIssue();
        routeSaveIssues();
        routeValidateCronExpression();
    }

    private void routeIndex(FreeMarkerEngine freeMarkerEngine) {
        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "issue-form.ftl");
        }, freeMarkerEngine);
    }

    private void routeProjects() {
        get("projects/", (request, response) -> {
            RedmineService redmineService = RedmineServiceFactory.createRedmineService();
            List<Project> projects = redmineService.getProjects();
            return dataToJson(projects);
        });
    }

    private void routeUsersByProject() {
        get("projects/:id/users/", (request, response) -> {
            String id = request.params(":id");
            RedmineService redmineService = RedmineServiceFactory.createRedmineService();
            List<User> users = redmineService.getUsersByProject(Integer.valueOf(id));
            return dataToJson(users);
        });
    }

    private void routeTrackersByProject() {
        get("projects/:id/trackers/", (request, response) -> {
            String id = request.params(":id");
            RedmineService redmineService = RedmineServiceFactory.createRedmineService();
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
            Long id = Long.parseLong(request.params(":id"));
            IssueService issueService = new IssueService();
            try {
                issueService.remove(id);
                return new ResponseMsg("Issue with id '%s' removed", idStr);
            } catch (Exception e) {
                response.status(400);
                return new ResponseMsg("No issue with id '%s' found", idStr);
            }
        });
    }

    private void routeSaveIssues() {
        post("issues/", (request, response) -> {
            IssueService issueService = new IssueService();
            ScheduledIssue scheduledIssue = JsonUtil.jsonToData(request.body(), ScheduledIssue.class);
            issueService.save(scheduledIssue);
            return new ResponseMsg("Success to add issue");
        });
    }

    private void routeValidateCronExpression() {
        post("issues/cron/validate", (request, response) -> {
            IssueService issueService = new IssueService();
            FieldValidate field = JsonUtil.jsonToData(request.body(), FieldValidate.class);
            boolean isValid = issueService.validateCron(field.getValue());
            return JsonUtil.dataToJson(ValidateResponse.of(isValid, field.getValue()));
        });
    }


}
