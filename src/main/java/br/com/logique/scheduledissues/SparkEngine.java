package br.com.logique.scheduledissues; /**
 * Created by Gustavo on 10/04/2016.
 */

import br.com.logique.scheduledissues.model.service.RedmineService;
import br.com.logique.scheduledissues.util.JsonUtil;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Project;
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
import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class SparkEngine {

    public void setUp() throws IOException {
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
        Configuration freeMarkerConfiguration = new Configuration(Configuration.getVersion());
        freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(SparkEngine.class, "/templates"));
        freeMarkerEngine.setConfiguration(freeMarkerConfiguration);
        staticFileLocation("/");
        routeIndex(freeMarkerEngine);
        routeUsersByProject();
    }

    private void routeIndex(FreeMarkerEngine freeMarkerEngine) {
        get("/", (request, response) -> {
            RedmineService redmineService = getRedmineService();
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("projects", tryGetProjects(redmineService));
            attributes.put("users", tryGetUsers(redmineService));
            return new ModelAndView(attributes, "issue-form.ftl");
        }, freeMarkerEngine);
    }

    private void routeUsersByProject() {
        get("/users/:id", (request, response) -> {
            String id = request.params(":id");
            RedmineService redmineService = getRedmineService();
            List<User> users = redmineService.getUsersByProject(Integer.valueOf(id));
            return dataToJson(users);
        });
    }

    private List<Project> tryGetProjects(RedmineService redmineService){
        try {
            return redmineService.getProjects();
        } catch (RedmineException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<User> tryGetUsers(RedmineService redmineService){
        try {
            return redmineService.getUsers();
        } catch (RedmineException e) {
            e.printStackTrace();
        }
        return null;
    }

    private RedmineService getRedmineService() {
        return new RedmineService.BuilderRedmineService()
                .withApiAccessKey("029b49eaa97e110f0ce5d8149cb4d622365eba58")
                .withURI("http://209.208.90.122/redmine")
                .build();
    }

}
