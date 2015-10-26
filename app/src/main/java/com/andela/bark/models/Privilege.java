package com.andela.bark.models;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by andela on 10/9/15.
 */

@ParseClassName("Privilege")
public class Privilege extends ParseObject{

    private static final String CLASSNAME = "Privilege";
    public Privilege(){
        super();
    }

    public Privilege (String name){
        super();
        put("name",name);
        put("level", validateRole(name));
    }

    public int getlevel(){
        return getNumber("level").intValue();
    }
    public String getPrivilegeName(){
        return getString("name");
    }


    public int validateRole(String role){
        switch (role){
            case "Admin":
               return 1;
            case "Manager":
                return 2;
            case "keeper":
                return 3;
            default: return 3;
        }
    }

    public static void fetchAll(QueryCallback callback){
        ParseQuery<ParseObject> rolesQuery = ParseQuery.getQuery(CLASSNAME);
        try {
            callback.onSuccess(rolesQuery.find());
        } catch (ParseException e) {
            callback.onError(e);
        }
    }

    public static void getPrivilegeWithName(String name, QueryCallback callback){
        ParseQuery<ParseObject> roleQuery = ParseQuery.getQuery(CLASSNAME);
        roleQuery.whereEqualTo("name",name).setLimit(1);
        try {
            callback.onSuccess(roleQuery.find());
        } catch (ParseException e) {
            callback.onError(e);
        }
    }
}
