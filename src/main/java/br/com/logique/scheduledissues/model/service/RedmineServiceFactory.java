package br.com.logique.scheduledissues.model.service;

/**
 * Created by Gustavo on 17/04/2016.
 */
public class RedmineServiceFactory {

    public static RedmineService createRedmineService() {
        return new RedmineService.BuilderRedmineService()
                .withApiAccessKey("029b49eaa97e110f0ce5d8149cb4d622365eba58")
                .withURI("http://209.208.90.122/redmine")
                .build();
    }

}
