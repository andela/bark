package com.andela.bark;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import com.andela.bark.models.User;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

/**
 * Created by andela-cj on 9/18/15.
 */
public class GateKeeperManager {
    private User keeper;
    private Role GKprRole;
    public boolean isAuthenticated;
    private Activity activity;

    public GateKeeperManager(User keep, Activity activity){
        this.activity = activity;
        isAuthenticated = false;
        keeper = keep;
        getUserRoles();
    }

    public void setKeeper (User keeper){
        this.keeper = keeper;
        getUserRoles();
    }

    public void authenticate(final ParseObject role) {
        ProgressDialog dialog = new ProgressDialog(activity);
        ParseQuery<ParseObject> usersQuery = ParseQuery.getQuery("Users");
        usersQuery.whereEqualTo("userID", keeper.getUserID()).include("role");

        dialog.setMessage("Fetching...");
        try {
            dialog.show();
            List<ParseObject> list = usersQuery.find();
            if (dialog.isShowing()) dialog.dismiss();

            if (list!= null && list.size() == 0) {
                createUser(role);
                isAuthenticated = true;
            } else {
                ParseObject user = list.remove(0);
                ParseObject userRole = user.getParseObject("role");
                if ((GKprRole = validateRole(userRole.getString("name"))) != null) {
                    isAuthenticated = true;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void getUserRoles() {

        ParseQuery<ParseObject> rolesQuery = ParseQuery.getQuery("Privilege");
        try {
            List<ParseObject> list = rolesQuery.find();
            authenticate(list.get(0));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void createUser(final ParseObject role) {
        ParseObject user = new ParseObject("Users");
        user.put("FirstName", keeper.getFirstName());
        user.put("LastName", keeper.getLastName());
        user.put("role", role);
        user.put("userID", keeper.getUserID());
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    keeper.setRole(role.getString("name"));
                }
            }
        });
    }


    public Role validateRole(String role){
        Role eRole;
        switch (role){
            case "Admin":
                eRole = Role.Admin; break;
            case "Manager":
                eRole = Role.Manager; break;
            case "keeper":
                eRole = Role.GateKeeper;
                break;
            default: eRole = null; break;
        }
        return eRole;
    }

    private enum Role{
        Admin, Manager, GateKeeper;
    }
}