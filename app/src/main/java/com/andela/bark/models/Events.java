package com.andela.bark.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Andela on 9/16/15.
 */
@ParseClassName("Events")
public class Events extends ParseObject {
    public Events() {
        super();
    }
    public String getName() {
        return getString("Name");
    }

    public String getId() {
        return getString("objectId");
    }

    public String getLocation() {
        return getString("Location");
    }
}
