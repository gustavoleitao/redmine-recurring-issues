package br.com.logique.scheduledissues.model.dao;

import br.com.logique.scheduledissues.model.domain.ScheduledIssue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gustavo on 14/04/2016.
 */
public class ScheduledIssuesDao implements Dao<ScheduledIssue> {

    Map<Integer,ScheduledIssue> database = new HashMap<>();

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
    public void remove(ScheduledIssue entity) {
        database.remove(entity.getId());
    }
}
