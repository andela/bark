package com.andela.bark.models;

/**
 * Created by Andela on 9/16/15.
 */
public class Event {

    private String name;
    private String id;
    private String location;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

}
