package com.andela.bark.models;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import com.andela.bark.GateKeeperManager;
import com.andela.bark.R;
import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by andela-cj on 26/10/2015.
 */
@ParseClassName("Ticket")
public class Ticket extends ParseObject {
    private static String CLASSNAME = "Ticket";

    public Ticket(){
        super(CLASSNAME);
    }

    public static int getNumberOfUsedTickets() {
        int num = 0;
        // num = Ticket.getUsed();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Ticket");
        query.whereEqualTo("used", true);
        try {
            num = query.count();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return num;
    }

    public static void validateTicketNumber(String ticketNumberInput, final QueryCallback callback){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(CLASSNAME);
        query.whereEqualTo("ticketNumber", ticketNumberInput).setLimit(1);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    callback.onSuccess(list);
                } else {
                    callback.onError(e);
                }
            }
        });
    }
}
