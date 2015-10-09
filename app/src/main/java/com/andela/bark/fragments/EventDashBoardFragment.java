package com.andela.bark.fragments;

/**
 * Created by andela-jugba on 10/9/15.
 */


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andela.bark.R;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class EventDashBoardFragment extends Fragment {
    private TextView tvNumberOfTicketsScanned;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_event_dash_board, container, false);
        tvNumberOfTicketsScanned = (TextView) v.findViewById(R.id.tvNumberOfTickets);
        tvNumberOfTicketsScanned.setText("Number of Tickets Scanned: " + getNumberOfUsedTickets());
        return v;

    }

    private int getNumberOfUsedTickets() {
        int num = 0;
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Ticket");
        query.whereEqualTo("used", true);
        try {
            num = query.count();
            Log.i("Number of used tickets", num + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return num;
    }

}
