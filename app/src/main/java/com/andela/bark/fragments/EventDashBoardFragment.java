package com.andela.bark.fragments;

/**
 * Created by andela-jugba on 10/9/15.
 */


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andela.bark.R;
import com.andela.bark.models.Ticket;

public class EventDashBoardFragment extends Fragment {
    private TextView tvNumberOfTicketsScanned;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.simplerow, container, false);
        tvNumberOfTicketsScanned = (TextView) v.findViewById(R.id.rowTextView);
        tvNumberOfTicketsScanned.setBackgroundColor(getResources().getColor(R.color.operand_color));
        tvNumberOfTicketsScanned.setText("Number of Tickets Scanned: " + Ticket.getNumberOfUsedTickets());
        return v;
    }


}
