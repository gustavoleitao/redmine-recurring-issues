package br.com.logique.scheduledissues.model.dao;

import br.com.logique.scheduledissues.model.domain.Project;
import br.com.logique.scheduledissues.model.domain.ScheduledIssueEntity;
import br.com.logique.scheduledissues.model.dto.ScheduledIssue;
import br.com.logique.scheduledissues.model.domain.Tracker;
import br.com.logique.scheduledissues.model.domain.User;

import java.util.*;

/**
 * Created by Gustavo on 14/04/2016.
 */
public class ScheduledIssuesDao implements Dao<ScheduledIssueEntity> {

    private static Map<Integer,ScheduledIssueEntity> database = new HashMap<>();

    static{
        database.put(0, createSimScheduled(0));
        database.put(1, createSimScheduled(1));
        database.put(2, createSimScheduled(2));
    }

    private static ScheduledIssueEntity createSimScheduled(int id) {
        ScheduledIssueEntity issue = new ScheduledIssueEntity();
        issue.setId(id);
        issue.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        issue.setPeriod("* * * * *");
        issue.setProjectId(0);
        issue.setTrackerId(0);
        issue.setTitle("Title");
        issue.setUserAssignedId(0);
        issue.setWatchersId(Arrays.asList(0,1));
        return issue;
    }

    @Override
    public ScheduledIssueEntity save(ScheduledIssueEntity entity) {
        database.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Collection<ScheduledIssueEntity> all() {
        return database.values();
    }

    @Override
    public ScheduledIssueEntity findByID(int id) {
        return database.get(id);
    }

    @Override
    public boolean remove(ScheduledIssueEntity entity) {
        return database.remove(entity.getId()) != null;
    }

    @Override
    public void merge(ScheduledIssueEntity entity) {
        if (entity.getId() != null){
            database.put(entity.getId(), entity);
        }
    }
}
