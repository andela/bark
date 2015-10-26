package com.andela.bark;

import android.app.Activity;
import android.app.ProgressDialog;

import com.andela.bark.models.Privilege;
import com.andela.bark.models.QueryCallback;
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
        final ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setMessage("Fetching...");
        dialog.show();
        User.getUserWithID(keeper.getUserID(), new QueryCallback() {

            @Override
            public void onSuccess(List<?> list) {
                if (list != null && list.size() == 0) {
                    if (dialog.isShowing()) dialog.dismiss();
                    createUserWithPrivilege();
                    isAuthenticated = true;
                } else {
                    User user = (User) list.remove(0);
                    Privilege userRole = (Privilege) user.getParseObject("role");
                    if (userRole.getObjectId().equals(role.getObjectId())) {
                        keeper = user;
                        isAuthenticated = true;
                    }
                }
            }
            @Override
            public void onError(Exception e) {
                if (dialog.isShowing()) dialog.dismiss();
                e.printStackTrace();
             }
         });
    }

    public void getUserRoles() {
        Privilege.fetchAll(new QueryCallback() {
            @Override
            public void onSuccess(List<?> list) {
                authenticate((Privilege)list.get(0));
            }
            @Override
            public void onError(Exception e) {
                e.printStackTrace();

            }
        });
    }

    public void createUserWithPrivilege(){
        Privilege.getPrivilegeWithName("Admin", new QueryCallback() {
            @Override
            public void onSuccess(List<?> list) {
                Privilege role = (Privilege) list.remove(0);
                keeper.setRole(role);
                keeper.update();
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }


    public static User getKeeper(){
        return keeper;
    }
}