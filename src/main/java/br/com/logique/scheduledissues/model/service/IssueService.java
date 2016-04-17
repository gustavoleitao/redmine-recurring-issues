package br.com.logique.scheduledissues.model.service;

import br.com.logique.scheduledissues.model.dao.Dao;
import br.com.logique.scheduledissues.model.dao.ScheduledIssuesDao;
import br.com.logique.scheduledissues.model.domain.ScheduledIssueEntity;
import br.com.logique.scheduledissues.model.dto.ScheduledIssue;
import br.com.logique.scheduledissues.util.ScheduledDtoToEntity;
import br.com.logique.scheduledissues.util.ScheduledEntityToDto;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Gustavo on 14/04/2016.
 */
public class IssueService {

    private ScheduledEntityToDto scheduledEntityToDto = new ScheduledEntityToDto();
    private ScheduledDtoToEntity scheduledDtoToEntity = new ScheduledDtoToEntity();

    private Dao<ScheduledIssueEntity> issuesDaoDao = new ScheduledIssuesDao();

    public void save(ScheduledIssue issue) {
        issuesDaoDao.save(scheduledDtoToEntity.apply(issue));
    }

    public Collection<ScheduledIssue> todos() {
        Collection<ScheduledIssue> scheduledIssues = new ArrayList<>();
        issuesDaoDao.all().stream().forEach(v -> scheduledIssues.add(scheduledEntityToDto.apply(v)));
        return scheduledIssues;
    }

    public void remove(Long id) {
        ScheduledIssue issue = new ScheduledIssue();
        issue.setId(id);
        issuesDaoDao.remove(scheduledDtoToEntity.apply(issue));
    }

}
