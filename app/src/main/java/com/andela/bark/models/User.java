package com.andela.bark.models;

import com.facebook.Profile;
import com.google.android.gms.plus.model.people.Person;

/**
 * Created by Andela on 9/17/15.
 */
public class User {

    private String objectId;
    private String userID;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String role;

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
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
