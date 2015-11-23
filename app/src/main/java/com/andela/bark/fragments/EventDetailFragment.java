package com.andela.bark.fragments;

import android.os.Bundle;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;

import android.widget.Button;


import com.andela.bark.R;

public class EventDetailFragment extends Fragment implements View.OnClickListener {

    private Button checkInButton;
    private Button dashboardButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        getActivity().setContentView(R.layout.fragment_container);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_detail, container, false);
        final Bundle args = getArguments();
        String event = args.getString("Text");
        getActivity().setTitle(event);

        dashboardButton = (Button)v.findViewById(R.id.event_dashboard);
        checkInButton = (Button)v.findViewById(R.id.manage_event);
        checkInButton.setOnClickListener(this);
        dashboardButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v ==  checkInButton) {
            TicketVerificationFragment ticket = new TicketVerificationFragment();
            ticket.setArguments(getArguments());
            replaceFragment(ticket);
        } else if (v == dashboardButton) {
            EventDashBoardFragment eventDashBoardFragment = new EventDashBoardFragment();
            eventDashBoardFragment.setArguments(getArguments());
            replaceFragment(eventDashBoardFragment);
        }
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }
}
