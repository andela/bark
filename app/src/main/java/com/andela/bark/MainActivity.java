package com.andela.bark;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.andela.bark.authentication.FacebookAuth;
import com.andela.bark.authentication.GoogleAuth;
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
import com.google.android.gms.plus.model.people.Person;
import com.parse.Parse;
import com.parse.ParseInstallation;


public class MainActivity extends Activity {
    private FacebookAuth fba = new FacebookAuth(this);
//    private GoogleAuth googleHandler;

    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fba.stuffInsideoncreate();

//        googleHandler = new GoogleAuth(this);

        try {
            Parse.initialize(this, "vKYBj5ToX5nVxINd0ubtBqoRo3EyHB5jcNLS7rNw", "zFYifD7N4dHLHFZ7Js05rOrhWdnl085RJSSrFK8W");
            ParseInstallation.getCurrentInstallation().saveInBackground();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);
        LoginButton loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.registerCallback(fba.getmCallbackManager(), fba.getmCallback());
    }


    @Override
    public void onResume(){
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        if(fba.getProfile() != null){
            Intent i  = new Intent(this, FragmentHostActivity.class);
            startActivity(i);
        }
        //fba.displayWelcomeMessage(profile);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
//        googleHandler.onActivityResult(requestCode, resultCode, data);
        fba.getmCallbackManager().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        googleHandler.connect();

    }

    @Override
    protected void onStop() {
        super.onStop();
//        googleHandler.disconnect();
        fba.getTracker().stopTracking();
        fba.getProfileTracker().stopTracking();
        fba.logOut();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}