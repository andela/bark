package com.andela.bark.fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andela.bark.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class TicketVerificationFragment extends Fragment {

    private EditText ticketNumber;
    private Button submitTicketNumber;
    private String ticketNumberInput;
    private String eventId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ticket_verification, container, false);

        final Bundle args = getArguments();
        String eventName = args.getString("Event");
        getActivity().setTitle(eventName);

        eventId = args.getString("EventId");



        ticketNumber = (EditText)v.findViewById(R.id.ticket_number_field);
        ticketNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ticketNumberInput = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        submitTicketNumber = (Button)v.findViewById(R.id.submit_ticket_number);

        submitTicketNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  validateTicketNumber(ticketNumberInput);
            }
        });

        return v;
    }

    public void validateTicketNumber(String ticketNumberInput){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Ticket");
        query.whereEqualTo("Event", eventId).whereEqualTo("ticketNumber", ticketNumberInput);
        //query.whereEqualTo("ticketNumber", ticketNumberInput);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e == null){
                    if(list.size() == 1){
                        ParseObject ticket = list.get(0);
                        Boolean used = ticket.getBoolean("used");
                        if(used){
                            Toast.makeText(getActivity(), "Ticket Has Been Used!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getActivity(), "Valid Ticket!", Toast.LENGTH_SHORT).show();
                            ticket.put("used",true);
                            ticket.saveInBackground();
                        }
                    }else{
                        Toast.makeText(getActivity(), "Ticket Not Valid", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Log.d("Error Message", e.getMessage());
                    Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}