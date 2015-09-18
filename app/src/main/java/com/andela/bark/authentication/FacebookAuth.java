package com.andela.bark.authentication;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;

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

    private Activity activity;
    private Profile userProfile;
    private AccessToken accessToken;
    private CallbackManager mCallbackManager;

    public FacebookAuth(Activity a){
        activity = a;
    }

    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {

        @Override
        public void onSuccess(LoginResult loginResult) {
            accessToken = loginResult.getAccessToken();
            userProfile = Profile.getCurrentProfile();

            if(userProfile != null){
                User user = User.createFacebookUser(userProfile);
                GKprManger gKprManger = new GKprManger(user);
                Intent i  = new Intent(activity, FragmentHostActivity.class);
                activity.startActivity(i);
            }
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };


    public void setupFacebookAuth(Activity activity){
        FacebookSdk.sdkInitialize(activity.getApplicationContext());
    }

    public void trackers(){
        AccessTokenTracker tracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken accessToken, AccessToken accessToken1) {

            }
        };

        ProfileTracker profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
            }
        };

        tracker.startTracking();
        profileTracker.startTracking();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void loginButton(Activity activity){
        LoginButton loginButton = (LoginButton)activity.findViewById(R.id.login_button);
        loginButton.registerCallback(mCallbackManager, mCallback);
    }

    public void setCallbackManager(){
        mCallbackManager = CallbackManager.Factory.create();
    }

    public void logOut(){
        LoginManager.getInstance().logOut();
    }

    public Profile userProfile(){
        return userProfile;
    }

    public AccessToken getAccessToken(){
        return accessToken;
    }
}