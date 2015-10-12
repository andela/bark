package com.andela.bark.ticketVerification;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import com.andela.bark.GateKeeperManager;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

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
                            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                            builder.setMessage("Ticket Has Been Used!")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            //do things
                                            restartScanner();
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                            builder.setMessage("Valid Ticket!")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            //do things
                                            restartScanner();
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();
                            ticket.put("scannedBy", GateKeeperManager.getKeeper());
                            ticket.put("used", true);
                            ticket.saveInBackground();
                        }
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setMessage("Ticket NOT Valid!")
                                .setCancelable(false)
                                .setPositiveButton("OK", null);
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                } else {
                    Log.d("Error Message", e.getMessage());
                    Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void restartScanner(){
        activity.finish();
        activity.startActivity(activity.getIntent());
    }
}
