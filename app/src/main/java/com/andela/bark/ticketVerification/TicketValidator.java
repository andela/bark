package com.andela.bark.ticketVerification;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andela.bark.GateKeeperManager;
import com.andela.bark.R;
import com.andela.bark.models.QueryCallback;
import com.andela.bark.models.Ticket;
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
    ProgressDialog progress;
    public TicketValidator(Activity activity) {
        this.activity = activity;
    }

    public void validateTicketNumber(String ticketNumberInput){
        progress = new ProgressDialog(activity);
        progress.setCancelable(false);
        progress.setMessage("Verifying...");
        progress.show();

        Ticket.validateTicketNumber(ticketNumberInput, new QueryCallback() {
            Dialog smiley;
            @Override
            public void onSuccess(List<?> list) {
                if (list.size() == 1) {
                    Ticket ticket = (Ticket) list.get(0);
                    Boolean used = ticket.getBoolean("used");
                    if (used) {
                        smiley = BuildSmileyDialog("Ticket Has Been Used!", R.drawable.frown, Color.RED);
                        if (progress.isShowing()) progress.dismiss();
                        if (!smiley.isShowing())
                            smiley.show();
                    } else {
                        smiley = BuildSmileyDialog("Valid Ticket!", R.drawable.smile, Color.GREEN);
                        smiley.setOnDismissListener(dismissListener);
                        if (progress.isShowing()) progress.dismiss();
                        if (!smiley.isShowing())
                            smiley.show();
                        // TODO use setValue for key in baseModel
                        ticket.put("scannedBy", GateKeeperManager.getKeeper());
                        ticket.put("used", true);
                        ticket.saveInBackground();
                    }
                } else {
                    smiley = BuildSmileyDialog("Ticket NOT Valid!", R.drawable.frown, Color.RED);
                    smiley.setOnDismissListener(dismissListener);
                    if (progress.isShowing()) progress.dismiss();
                    if (!smiley.isShowing())
                        smiley.show();

                }
            }

            @Override
            public void onError(Exception e) {
                Log.d("Error Message", e.getMessage());
                Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void restartScanner(){
        activity.finish();
        activity.startActivity(activity.getIntent());
    }


    DialogInterface.OnDismissListener dismissListener = new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {
        // restartScanner();

        }
    };


    private Dialog BuildSmileyDialog(String message, int resourceId, int color){
        Dialog a = new Dialog(activity);
        a.requestWindowFeature(Window.FEATURE_NO_TITLE);
        a.setContentView(R.layout.smile);
        PorterDuff.Mode mMode = PorterDuff.Mode.SRC_IN;
        ImageView f = ((ImageView) a.findViewById(R.id.smileImage));
        TextView v = (TextView) a.findViewById(R.id.message);
        Drawable ojo = activity.getResources().getDrawable(resourceId);
        if (ojo !=null){
            ojo.setColorFilter(color, mMode);
            f.setImageDrawable(ojo);
            a.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
            v.setText(message);
        }
        return a;
    }
}
