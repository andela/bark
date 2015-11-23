package com.andela.bark;

import android.app.Application;

import com.andela.bark.models.Events;
import com.andela.bark.models.Privilege;
import com.andela.bark.models.User;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;

/**
 * Created by oluwatosin on 23/11/2015.
 */
public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initializeParseObjects();
    }
    private void initializeParseObjects() {
        try {
            ParseObject.registerSubclass(Events.class);
            ParseObject.registerSubclass(Privilege.class);
            ParseObject.registerSubclass(User.class);
            Parse.initialize(this, "vKYBj5ToX5nVxINd0ubtBqoRo3EyHB5jcNLS7rNw", "zFYifD7N4dHLHFZ7Js05rOrhWdnl085RJSSrFK8W");
            ParseInstallation.getCurrentInstallation().saveInBackground();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

