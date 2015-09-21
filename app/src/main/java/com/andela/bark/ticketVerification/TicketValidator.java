package com.andela.bark.ticketVerification;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by andela on 9/21/15.
 */
public class TicketValidator {
    Activity activity;
    public TicketValidator(Activity activity) {
        this.activity = activity;
    }

    public void validateTicketNumber(String ticketNumberInput){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Ticket");
        query.whereEqualTo("ticketNumber", ticketNumberInput);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    if (list.size() == 1) {
                        ParseObject ticket = list.get(0);
                        Boolean used = ticket.getBoolean("used");
                        if (used) {
                            Toast.makeText(activity, "Ticket Has Been Used!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(activity, "Valid Ticket!", Toast.LENGTH_SHORT).show();
                            ticket.put("used", true);
                            ticket.saveInBackground();
                        }
                    } else {
                        Toast.makeText(activity, "Ticket Not Valid", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("Error Message", e.getMessage());
                    Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
