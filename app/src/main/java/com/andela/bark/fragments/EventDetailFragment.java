package com.andela.bark.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.Toast;

import com.andela.bark.FragmentHostActivity;
import com.andela.bark.R;

public class EventDetailFragment extends Fragment {

    private Button manageEventButton;
    private Button dashboardButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_event_detail, container, false);
        final Bundle args = getArguments();
        String event = args.getString("Event");
        String eventId = args.getString("EventId");

        getActivity().setTitle(event);
        //getActivity().setTitle(eventId);
        //final Bundle args2 = new Bundle();
        //args2.putString("EventId", eventId);


        manageEventButton = (Button)v.findViewById(R.id.manage_event);
        manageEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TicketVerificationFragment ticket = new TicketVerificationFragment();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
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
                Toast.makeText(getActivity(), "Dashboard Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}