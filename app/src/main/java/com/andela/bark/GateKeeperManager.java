package com.andela.bark;

import android.app.Activity;
import android.app.ProgressDialog;

import com.andela.bark.models.Privilege;
import com.andela.bark.models.User;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

/**
 * Created by andela-cj on 9/18/15.
 */
public class GateKeeperManager {
    private static User keeper;
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

    public void authenticate(final Privilege role) {
        ProgressDialog dialog = new ProgressDialog(activity);
        ParseQuery<ParseObject> usersQuery = ParseQuery.getQuery("User");
        usersQuery.whereEqualTo("userID", keeper.getUserID()).include("role");

        dialog.setMessage("Fetching...");
        try {
            dialog.show();
            List<ParseObject> list = usersQuery.find();
            if (dialog.isShowing()) dialog.dismiss();

            if (list!= null && list.size() == 0) {
                createUserWithPrivilege();
                isAuthenticated = true;
            } else {
                User user = (User) list.remove(0);
                Privilege userRole = (Privilege)user.getParseObject("role");
                if ( userRole.getObjectId().equals(role.getObjectId())) {
                    keeper = user;
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
            authenticate((Privilege)list.get(0));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void createUserWithPrivilege() throws ParseException {
        ParseQuery<ParseObject> roleQuery = ParseQuery.getQuery("Privilege");
        List <ParseObject> list = roleQuery.whereEqualTo("name","Admin").find();
        Privilege role = (Privilege) list.remove(0);
        keeper.put("role", role);
        keeper.saveInBackground();
    }


    public static User getKeeper(){
        return keeper;
    }


    private enum Role{
        Admin, Manager, GateKeeper;
    }
}