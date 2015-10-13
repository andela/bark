package com.andela.bark.fragments;

import android.os.Bundle;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;

import android.widget.Button;
import android.widget.Toast;


import com.andela.bark.FragmentHostActivity;
import com.andela.bark.R;

public class EventDetailFragment extends Fragment {

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


        checkInButton = (Button)v.findViewById(R.id.manage_event);
        checkInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TicketVerificationFragment ticket = new TicketVerificationFragment();
                FragmentManager fragmentManager = getFragmentManager();
                ticket.setArguments(args);

                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, ticket)
                        .addToBackStack(null)
                        .commit();
            }
        });

        dashboardButton = (Button)v.findViewById(R.id.event_dashboard);
        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventDashBoardFragment eventDashBoardFragment = new EventDashBoardFragment();
                FragmentManager fragmentManager = getFragmentManager();
                eventDashBoardFragment.setArguments(args);

                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, eventDashBoardFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return v;
    }
}
