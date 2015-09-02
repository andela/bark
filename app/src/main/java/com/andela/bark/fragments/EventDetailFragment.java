package com.andela.bark.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andela.bark.FragmentHostActivity;
import com.andela.bark.R;

public class EventDetailFragment extends android.support.v4.app.Fragment {

    private String event;

    public EventDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_event_detail, container, false);
        inflateEventDetail();

        return v;
    }

    public void inflateEventDetail() {
        Bundle args = getArguments();
        event = args.getString("Event");

        // Set action bar title
        ((FragmentHostActivity)getActivity()).setActionBarTitle(event);
    }

}
