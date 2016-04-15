package br.com.logique.scheduledissues.model.service;

import br.com.logique.scheduledissues.model.dao.Dao;
import br.com.logique.scheduledissues.model.dao.ScheduledIssuesDao;
import br.com.logique.scheduledissues.model.domain.ScheduledIssue;

import java.util.Collection;

/**
 * Created by Gustavo on 14/04/2016.
 */
public class IssueService {

    private Dao<ScheduledIssue> issuesDaoDao = new ScheduledIssuesDao();

    public void save(ScheduledIssue issue){
        issuesDaoDao.save(issue);
    }

    public void merge(ScheduledIssue issue){
        issuesDaoDao.merge(issue);
    }

    public Collection<ScheduledIssue> todos(){
        return issuesDaoDao.all();
    }

    public boolean remove(int id){
        ScheduledIssue issue = new ScheduledIssue();
        issue.setId(id);
        return issuesDaoDao.remove(issue);
    }

}
