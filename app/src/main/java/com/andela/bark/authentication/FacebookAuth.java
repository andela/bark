package com.andela.bark.authentication;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.andela.bark.FragmentHostActivity;

import com.andela.bark.FragmentHostActivity;
import com.andela.bark.GKprManger;
import com.andela.bark.R;
import com.andela.bark.models.User;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import com.facebook.login.widget.LoginButton;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Jibola on 8/31/15.
 */
public class FacebookAuth {

    private Profile profile;
    private Activity activity;
    public FacebookAuth(Activity activity){
        this.activity = activity;
    }

    private CallbackManager mCallbackManager;
    public void setup(){
        setupFacebookAuth(activity);
    }

    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {

        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            displayWelcomeMessage(profile);
            profile = Profile.getCurrentProfile();

            if(profile != null){
                User user = User.createFacebookUser(profile);
                GKprManger gKprManger = new GKprManger(user, activity);
                if (gKprManger.isAuthenticated){
                    Intent i  = new Intent(activity, FragmentHostActivity.class);
                    activity.startActivity(i);
                }
            }
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };
    private AccessTokenTracker tracker;
    private ProfileTracker profileTracker;

    public void displayWelcomeMessage(Profile profile) {
        if (profile != null) {
            Toast.makeText(activity.getApplicationContext(), "Welcome " + profile.getName(), Toast.LENGTH_LONG).show();
            Log.i("profileid", profile.getId());
        } else {
            Toast.makeText(activity.getApplicationContext(), "Problem with login ", Toast.LENGTH_LONG).show();

        }
    }

    public void setupFacebookAuth(Activity activity){
        FacebookSdk.sdkInitialize(activity.getApplicationContext());
    }

    public void stuffInsideoncreate(){
        FacebookSdk.sdkInitialize(activity.getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();
        tracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {

            }
        };
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                displayWelcomeMessage(newProfile);
            }
        };
        tracker.startTracking();
        profileTracker.startTracking();
    }

    public CallbackManager getmCallbackManager(){
        return mCallbackManager;
    }

    public FacebookCallback<LoginResult> getmCallback(){
        return mCallback;
    }

    public AccessTokenTracker getTracker(){
        return tracker;
    }

    public ProfileTracker getProfileTracker(){
        return profileTracker;
    }

    public Profile getProfile(){
        return profile;
    }

    public void logOut(){
        LoginManager.getInstance().logOut();
    }


}