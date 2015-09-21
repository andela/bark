package com.andela.bark.fragments;


import android.content.Intent;

import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.andela.bark.R;
import com.andela.bark.ticketVerification.TicketValidator;
import com.andela.bark.activities.QRCodeScanner;



public class TicketVerificationFragment extends Fragment{

    private EditText ticketNumber;
    private Button submitTicketNumber;
    private Button scanBarcode;

    private String ticketNumberInput;
    private String eventId;
    private TicketValidator ticketValidator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ticketValidator = new TicketValidator(getActivity());
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
                ticketValidator.validateTicketNumber(ticketNumberInput);
            }
        });

        scanBarcode = (Button)v.findViewById(R.id.scan_button);
        scanBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), QRCodeScanner.class);
                i.putExtra("EventId",eventId);
                getActivity().startActivity(i);
            }
        });

        return v;
    }

}