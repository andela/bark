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

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by andela-jugba on 8/31/15.
 */
public class EventListFragment extends android.support.v4.app.Fragment {
    private ArrayAdapter<String> listAdapter;
    private ListView mainListView;
    private String[] events;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                String event = events[position];
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
        // Create and populate dummy events
        events = new String[] { "BootcampX", "NextEvents", "Fresh", "BurningMan",
                "Sex And The City", "New Moon", "The Catch", "Neptune Mix"};
        ArrayList<String> eventList = new ArrayList<String>();
        eventList.addAll(Arrays.asList(events));

        // Create ArrayAdapter using the event list
        listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.simplerow, eventList);

        // Set action bar title
        ((FragmentHostActivity)getActivity()).setActionBarTitle("Event List");
    }
}