package br.com.logique.scheduledissues.model.domain;

import br.com.logique.scheduledissues.model.dao.GenericEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Gustavo on 14/04/2016.
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackerBasic extends GenericEntity {

    @Id
    private Long id;

    private String name;

    public TrackerBasic() {
    }

    public TrackerBasic(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
