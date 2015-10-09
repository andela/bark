package com.andela.bark.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by andela on 10/9/15.
 */

@ParseClassName("Privilege")
public class Privilege extends ParseObject{
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
}
