package com.andela.bark.authentication;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.andela.bark.activities.FragmentHostActivity;
import com.andela.bark.GateKeeperManager;
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
    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {

        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            profile = Profile.getCurrentProfile();
                Intent i  = new Intent(activity, FragmentHostActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.startActivity(i);
                activity.finish();
                displayWelcomeMessage(profile);
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

    public void displayWelcomeMessage(Profile profile){
        if(profile != null){
            User user = User.createFacebookUser(profile);
            GateKeeperManager kprManger = new GateKeeperManager(user, activity);
            if(kprManger.isAuthenticated){
                Intent i  = new Intent(activity, FragmentHostActivity.class);
                activity.startActivity(i);
            }            Toast.makeText(activity.getApplicationContext(), "Welcome " + profile.getName(), Toast.LENGTH_LONG).show();
            Log.i("profileid", profile.getId());

        }
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