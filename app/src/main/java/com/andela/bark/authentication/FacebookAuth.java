package com.andela.bark.authentication;


import android.app.Activity;
import android.content.Intent;

import com.andela.bark.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by Jibola on 8/31/15.
 */
public class FacebookAuth {

    private Activity activity;

    private Profile userProfile;
    private AccessToken accessToken;

    private CallbackManager mCallbackManager;
    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {


        @Override
        public void onSuccess(LoginResult loginResult) {
            accessToken = loginResult.getAccessToken();
            userProfile = Profile.getCurrentProfile();

            Intent i = new Intent(activity, EventListFragmentContainerActivity.class);
            activity.startActivity(i);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };

    public FacebookAuth(Activity a){
        activity = a;
    }


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

    public Profile userProfile(){
        return userProfile;
    }

    public AccessToken getAccessToken(){
        return accessToken;
    }
}