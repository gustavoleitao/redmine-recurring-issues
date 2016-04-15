package br.com.logique.scheduledissues.model.domain;

import java.util.List;

/**
 * Created by gustavo on 15/04/2016.
 */
public class ScheduledIssueEntity {

    private Integer id;

    private String period;

    private String title;

    private Integer projectId;

    private Integer trackerId;

    private Integer userAssignedId;

    private List<Integer> watchersId;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(Integer trackerId) {
        this.trackerId = trackerId;
    }

    public Integer getUserAssignedId() {
        return userAssignedId;
    }

    public void setUserAssignedId(Integer userAssignedId) {
        this.userAssignedId = userAssignedId;
    }

    public List<Integer> getWatchersId() {
        return watchersId;
    }

    public void setWatchersId(List<Integer> watchersId) {
        this.watchersId = watchersId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
