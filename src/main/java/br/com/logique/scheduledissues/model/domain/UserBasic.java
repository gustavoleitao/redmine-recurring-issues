package br.com.logique.scheduledissues.model.domain;

import br.com.logique.scheduledissues.model.dao.GenericEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Gustavo on 14/04/2016.
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBasic extends GenericEntity {

    @Id
    private Long id;

    private String fullName;

    @OneToMany(mappedBy = "userAssigned")
    private List<ScheduledIssueEntity> schedulesAssigneds;

    @ManyToMany(mappedBy = "watchers")
    private List<ScheduledIssueEntity> scheduledsWatchers;

    public UserBasic() {
    }

    public UserBasic(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserBasic userBasic = (UserBasic) o;

        return new EqualsBuilder()
                .append(id, userBasic.id)
                .append(fullName, userBasic.fullName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(fullName)
                .toHashCode();
    }
}
