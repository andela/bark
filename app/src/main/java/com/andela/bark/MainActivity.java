package com.andela.bark;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.andela.bark.authentication.FacebookAuth;
import com.andela.bark.authentication.GoogleAuth;
import com.facebook.Profile;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.plus.model.people.Person;
import com.andela.bark.models.Events;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;


public class MainActivity extends Activity {
    private FacebookAuth fba = new FacebookAuth(this);
    private GoogleAuth googleHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fba.stuffInsideoncreate();
        googleHandler = new GoogleAuth(this);

        try {
            ParseObject.registerSubclass(Events.class);
            Parse.initialize(this, getResources().getString(R.string.cid), getResources().getString(R.string.aid));
            ParseInstallation.getCurrentInstallation().saveInBackground();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        fba.getmCallbackManager().onActivityResult(requestCode, resultCode, data);
        googleHandler.onActivityResult(requestCode, resultCode, data);
        }


    @Override
    protected void onStop() {
        super.onStop();
//        googleHandler.disconnect();
        fba.getTracker().stopTracking();
        fba.getProfileTracker().stopTracking();
        fba.logOut();
        googleHandler.disconnect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}