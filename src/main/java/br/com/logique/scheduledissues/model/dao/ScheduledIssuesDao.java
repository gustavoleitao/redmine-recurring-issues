package br.com.logique.scheduledissues.model.dao;

import br.com.logique.scheduledissues.model.domain.Project;
import br.com.logique.scheduledissues.model.domain.ScheduledIssue;
import br.com.logique.scheduledissues.model.domain.Tracker;
import br.com.logique.scheduledissues.model.domain.User;

import java.util.*;

/**
 * Created by Gustavo on 14/04/2016.
 */
public class ScheduledIssuesDao implements Dao<ScheduledIssue> {

    private static Map<Integer,ScheduledIssue> database = new HashMap<>();

    static{
        database.put(0, createSimScheduled(0));
        database.put(1, createSimScheduled(1));
        database.put(2, createSimScheduled(2));
    }

    private static ScheduledIssue createSimScheduled(int id) {
        ScheduledIssue issue = new ScheduledIssue();
        issue.setId(id);
        issue.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        issue.setPeriod("* * * * *");
        issue.setProject(new Project(0, "Projectção"+id));
        issue.setTracker(new Tracker(0, "Tracker"+id));
        issue.setTitle("Title");
        issue.setUserAssigned(new User(0, "user01"));
        issue.setWatchers(Arrays.asList(new User(0, "user01"),new User(1, "user02")));
        return issue;
    }

    @Override
    public ScheduledIssue save(ScheduledIssue entity) {
        database.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Collection<ScheduledIssue> all() {
        return database.values();
    }

    @Override
    public ScheduledIssue findByID(int id) {
        return database.get(id);
    }

    @Override
    public boolean remove(ScheduledIssue entity) {
        return database.remove(entity.getId()) != null;
    }

    @Override
    public void merge(ScheduledIssue entity) {
        if (entity.getId() != null){
            database.put(entity.getId(), entity);
        }
    }
}
