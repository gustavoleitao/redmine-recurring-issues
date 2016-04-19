package br.com.logique.scheduledissues.model.domain;

import br.com.logique.scheduledissues.model.dao.GenericEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by gustavo on 15/04/2016.
 */
@Entity
public class ScheduledIssueEntity extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String period;

    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    private ProjectBasic project;

    @ManyToOne(fetch = FetchType.EAGER)
    private TrackerBasic tracker;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserBasic userAssigned;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "scheduler_watcher", joinColumns = {
            @JoinColumn(name = "scheduler_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "watcher_id",
                    nullable = false, updatable = false)})
    private List<UserBasic> watchers;

    private String description;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ProjectBasic getProject() {
        return project;
    }

    public void setProject(ProjectBasic project) {
        this.project = project;
    }

    public TrackerBasic getTracker() {
        return tracker;
    }

    public void setTracker(TrackerBasic tracker) {
        this.tracker = tracker;
    }

    public UserBasic getUserAssigned() {
        return userAssigned;
    }

    public void setUserAssigned(UserBasic userAssigned) {
        this.userAssigned = userAssigned;
    }

    public List<UserBasic> getWatchers() {
        return watchers;
    }

    public void setWatchers(List<UserBasic> watchers) {
        this.watchers = watchers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
