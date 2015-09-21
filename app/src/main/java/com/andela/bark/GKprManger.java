package com.andela.bark;

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
public class GKprManger {
    private User keeper;
    private Role GKprRole;
    public boolean isAuthenticated;

    public GKprManger (User keep){
        isAuthenticated = false;
        keeper = keep;
        getUserRoles();
    }

    public void setKeeper (User keeper){
        this.keeper = keeper;
        getUserRoles();
    }

    public void authenticate(final ParseObject role) {
        ParseQuery<ParseObject> usersQuery = ParseQuery.getQuery("Users");
        usersQuery.whereEqualTo("userID", keeper.getUserID());
        usersQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    if (list.size() == 0) {
                        createUser(role);
                    } else {
                        ParseObject user = list.remove(0);
                        ParseObject role = user.getParseObject("role");
                        if (GKprRole.validateRole(role.getString("name"))!= null){
                             isAuthenticated = true;
                        }
                    }
                } else {
                    Log.d("User query error", "An error occurred");
                }
            }
        });
    }

    public void getUserRoles() {
        ParseQuery<ParseObject> rolesQuery = ParseQuery.getQuery("Privilege");
        rolesQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    if (list.size() > 0) {
                        authenticate(list.get(0));
                    }
                }
            }
        });
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



    private enum Role{
        Admin, Manager, GateKeeper;
        Role role;
        public Role validateRole(String role){
            switch (role){
                case "Admin":
                    this.role = Admin; break;
                case "Manager":
                    this.role = Manager; break;
                case "keeper":
                    this.role = GateKeeper;
                    break;
                default: this.role = null; break;
            }
            return this.role;
        }
    }
}
