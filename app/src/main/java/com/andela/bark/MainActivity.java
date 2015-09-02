package com.andela.bark;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.andela.bark.authentication.FacebookAuth;
import com.andela.bark.authentication.GoogleAuth;
import com.google.android.gms.plus.model.people.Person;


public class MainActivity extends Activity {

    private FacebookAuth facebookAuth = new FacebookAuth();
    private GoogleAuth googleHandler;

    /* Object used to hold logged in user info */
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        facebookAuth.setupFacebookAuth(this);
        facebookAuth.setCallbackManager();
        facebookAuth.trackers();

        setContentView(R.layout.activity_main);
        googleHandler = new GoogleAuth(this);

        facebookAuth.loginButton(this);
    }

    @Override
    public void onResume(){
        super.onResume();
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
    }
}