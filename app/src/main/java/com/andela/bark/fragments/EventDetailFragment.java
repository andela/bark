package com.andela.bark.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.andela.bark.FragmentHostActivity;
import com.andela.bark.R;

public class EventDetailFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_event_detail, container, false);
        Bundle args = getArguments();
        String event = args.getString("Event");
        ((FragmentHostActivity)getActivity()).setActionBarTitle(event);
        return v;
    }
}
