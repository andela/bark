package com.andela.bark.models;

import android.util.Log;

import com.facebook.Profile;
import com.google.android.gms.plus.model.people.Person;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

/**
 * Created by Andela on 9/17/15.
 */
@ParseClassName("User")
public class User extends ParseObject{

    public User(){
        super();
    }

    public void setUserID(String userID) {
        put("userID", userID);
    }

    public String getUserID() {
        return getString("userID");
    }

    public void setFirstName(String firstName) {
        put("FirstName", firstName);
    }

    public String getFirstName() {
        return getString("FirstName");
    }

    public void setLastName(String lastName) {
        put("LastName", lastName);
    }

    public String getLastName() {
        return getString("LastName");
    }

    public void setEmailAddress(String emailAddress) {
        put("emailAddress", emailAddress);
    }

    public String getEmailAddress() {
        return getString("emailAddress");
    }

    public void setRole(Privilege role) {
        put("role",role);
    }

    public Privilege getRole() {
        return (Privilege) getParseObject("role");
    }

    public static User createFacebookUser(Profile userProfile) {
        User user = new User();
        user.setFirstName(userProfile.getFirstName());
        user.setLastName(userProfile.getLastName());
        user.setUserID(userProfile.getId());
        return user;
    }
    public static User createGoogleUser(Person person) {
        User user = new User();
        user.setFirstName(person.getName().getGivenName());
        user.setLastName(person.getName().getFamilyName());
        user.setUserID(person.getId());
        return user;
    }
}