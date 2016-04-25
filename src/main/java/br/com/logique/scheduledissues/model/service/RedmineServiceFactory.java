package br.com.logique.scheduledissues.model.service;

/**
 * Created by Gustavo on 17/04/2016.
 */
public class RedmineServiceFactory {

    private static String REDMINE_KEY = System.getProperty("redmine.key", "");
    private static String REDMINE_URL = System.getProperty("redmine.url", "");

    public static RedmineService createRedmineService() {
        return new RedmineService.BuilderRedmineService()
                .withApiAccessKey(REDMINE_KEY)
                .withURI(REDMINE_URL)
                .build();
    }

}
