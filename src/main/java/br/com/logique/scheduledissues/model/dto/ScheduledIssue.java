package br.com.logique.scheduledissues.model.dto;

import br.com.logique.scheduledissues.model.domain.ProjectBasic;
import br.com.logique.scheduledissues.model.domain.TrackerBasic;
import br.com.logique.scheduledissues.model.domain.UserBasic;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Gustavo on 14/04/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduledIssue {

    private Long id;

    private String period;

    private String title;

    private ProjectBasic project;

    private TrackerBasic tracker;

    private UserBasic userAssigned;

    private List<UserBasic> watchers;

    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
