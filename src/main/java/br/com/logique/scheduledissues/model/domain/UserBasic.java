package br.com.logique.scheduledissues.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Gustavo on 14/04/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBasic {

    public UserBasic() {
    }

    public UserBasic(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    private int id;

    private String fullName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
