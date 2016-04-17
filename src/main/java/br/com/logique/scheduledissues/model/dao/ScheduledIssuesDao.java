package br.com.logique.scheduledissues.model.dao;

import br.com.logique.scheduledissues.model.domain.ScheduledIssueEntity;

import javax.persistence.EntityManager;
import java.util.*;

/**
 * Created by Gustavo on 14/04/2016.
 */
public class ScheduledIssuesDao extends JpaAbstractDao<ScheduledIssueEntity> {

    public ScheduledIssuesDao() {
        super();
    }

}
