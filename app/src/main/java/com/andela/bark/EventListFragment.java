package com.andela.bark;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by andela on 8/31/15.
 */
public class EventListFragment extends android.support.v4.app.Fragment {
    private ArrayAdapter<String> listAdapter;
    private ListView mainListView;

    private static final String REQUEST_STRING = "Events";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(getActivity(), "vKYBj5ToX5nVxINd0ubtBqoRo3EyHB5jcNLS7rNw","zFYifD7N4dHLHFZ7Js05rOrhWdnl085RJSSrFK8W");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_eventlist,container,false);

        // Find the ListView resource.
        mainListView = (ListView) v.findViewById( R.id.mainListView );

        inflateEventList();

        mainListView.setAdapter(listAdapter);

        return v;
    }

    private void inflateEventList(){
        // Create and populate dummy events

         List<ParseObject> object;
        ParseQuery<ParseObject> query = ParseQuery.getQuery(REQUEST_STRING);
        try {
            ArrayList<String> s = new ArrayList<String>();
            object = query.find();
            for (ParseObject ob : object){
                Event event = new Event();
                event.name = ob.getString("Name");
                event.id = ob.getObjectId();

                s.add(event.name);
            }
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.simplerow, s.toArray(new String[0]));

        }catch (com.parse.ParseException e){
            e.printStackTrace();
        }
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> list, com.parse.ParseException e) {
//                if(e ==null){
//                        updateArrayAdapter(list);
////                    object = list;
////                    object = Arrays.asList(list.toArray(new String[0]));
//                }
//            }
//        });


//        String[] events = new String[] { "BootcampX", "NextEvents", "Fresh", "BurningMan",
//                "Sex And The City", "New Moon", "The Catch", "Neptune Mix"};
//        ArrayList<String> eventList = new ArrayList<String>();
//        eventList.addAll(Arrays.asList(events));

        // Create ArrayAdapter using the event list

//        listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.simplerow, eventList);
    }
//
//    void updateArrayAdapter(List <ParseObject> list){
//
//
//    }

    protected class Event {
        String name;
        String id;
    }
}
