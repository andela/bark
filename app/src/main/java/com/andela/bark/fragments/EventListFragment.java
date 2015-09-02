package com.andela.bark.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.andela.bark.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by andela-jugba on 8/31/15.
 */
public class EventListFragment extends android.support.v4.app.Fragment {
    private ArrayAdapter<String> listAdapter;
    private ListView mainListView;

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

        return v;
    }

    private void inflateEventList(){
        // Create and populate dummy events
        String[] events = new String[] { "BootcampX", "NextEvents", "Fresh", "BurningMan",
                "Sex And The City", "New Moon", "The Catch", "Neptune Mix"};
        ArrayList<String> eventList = new ArrayList<String>();
        eventList.addAll(Arrays.asList(events));

        // Create ArrayAdapter using the event list
        listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.simplerow, eventList);
    }
}