package com.andela.bark.models;

import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andela-cj on 26/10/2015.
 */
@ParseClassName("Events")
public class Events extends ParseObject {
    private static String CLASSNAME = "Events";

    public Events() {
        super(CLASSNAME);
    }


    public static void getAll(final QueryCallback callback) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASSNAME);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (list != null) {
                    ArrayList<Events> events = new ArrayList<Events>();
                    for (ParseObject ob: list ) {
                        Events a =  new Events();
                        a.put("Name", ob.get("Name"));
                        a.put("Location", ob.get("Location"));
                        a.setObjectId(a.getObjectId());
                        events.add(a);
                    }
                    callback.onSuccess(events);
                } else {
                    Exception exception = new Exception("List is Null");
                    callback.onError(exception);
                }
            }
        });
    }

}
