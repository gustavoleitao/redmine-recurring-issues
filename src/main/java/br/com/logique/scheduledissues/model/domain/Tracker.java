package br.com.logique.scheduledissues.model.domain;

/**
 * Created by Gustavo on 14/04/2016.
 */
public class Tracker {

    public Tracker() {
    }

    public Tracker(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
