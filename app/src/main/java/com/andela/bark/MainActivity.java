package com.andela.bark;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.andela.bark.authentication.FacebookAuth;
import com.andela.bark.authentication.GoogleAuth;
import com.google.android.gms.plus.model.people.Person;
import com.parse.Parse;
import com.parse.ParseInstallation;


public class MainActivity extends Activity {

    private FacebookAuth facebookAuth;
    private GoogleAuth googleHandler;

    /* Object used to hold logged in user info */
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        facebookAuth = new FacebookAuth(this);

        facebookAuth.setupFacebookAuth(this);
        facebookAuth.setCallbackManager();
        facebookAuth.trackers();

        setContentView(R.layout.activity_main);
        facebookAuth.loginButton(this);

        googleHandler = new GoogleAuth(this);

        try {
            Parse.initialize(this, getResources().getString(R.string.cid), getResources().getString(R.string.aid));
            ParseInstallation.getCurrentInstallation().saveInBackground();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onResume(){
        super.onResume();
        facebookAuth.logOut();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        facebookAuth.onActivityResult(requestCode, resultCode, data);
        googleHandler.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleHandler.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleHandler.disconnect();
        facebookAuth.logOut();
    }

}
