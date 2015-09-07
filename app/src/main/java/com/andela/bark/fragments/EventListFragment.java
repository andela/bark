package com.andela.bark.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.andela.bark.FragmentHostActivity;
import com.andela.bark.R;
import com.andela.bark.fragments.EventDetailFragment;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andela on 8/31/15.
 */
public class EventListFragment extends android.support.v4.app.Fragment {
    private ArrayAdapter<String> listAdapter;
    private ListView mainListView;
    private List<ParseObject> object;

    private static final String REQUEST_STRING = "Events";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Events");
        Parse.initialize(getActivity(), "vKYBj5ToX5nVxINd0ubtBqoRo3EyHB5jcNLS7rNw", "zFYifD7N4dHLHFZ7Js05rOrhWdnl085RJSSrFK8W");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_eventlist,container,false);


        // Find the ListView resource.
        mainListView = (ListView) v.findViewById( R.id.mainListView );

        inflateEventList();

        mainListView.setAdapter(listAdapter);
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ParseObject obj = object.get(position);
                String event = obj.getString("Name");
                Bundle args = new Bundle();
                args.putString("Event", event);

                EventDetailFragment eventDetailFragment = new EventDetailFragment();
                eventDetailFragment.setArguments(args);

                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, eventDetailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return v;
    }

    private void inflateEventList(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(REQUEST_STRING);
        try {
            ArrayList<String> s = new ArrayList<String>();
            object = query.find();
            for (ParseObject ob : object){
                Event event = new Event();
                event.name = ob.getString("Name");
                event.location = ob.getString("location");
                event.id = ob.getObjectId();

                s.add(event.name);
            }
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.simplerow, s.toArray(new String[0]));
            ((FragmentHostActivity)getActivity()).setActionBarTitle("Event List");

        }catch (com.parse.ParseException e){
            e.printStackTrace();
        }
    }


    protected class Event {
        String name;
        String id;
        String location;
    }
}
