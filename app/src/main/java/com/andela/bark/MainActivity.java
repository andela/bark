package com.andela.bark;

import android.app.Activity;
<<<<<<< HEAD
<<<<<<< HEAD
import android.content.Intent;
=======
=======
import android.content.Intent;
<<<<<<< HEAD
import android.content.IntentSender;
<<<<<<< HEAD
>>>>>>> 0f33e59... Google sign in achieved
import android.support.v7.app.ActionBarActivity;
>>>>>>> e1dd5ff... App logo placeholder
=======
>>>>>>> ba994c6... Removed unused import and deleted redundant boilerplate code
import android.os.Bundle;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
>>>>>>> 6c14333... Added google api client initialization cade to Main activity
=======
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
>>>>>>> 0f33e59... Google sign in achieved
=======
import android.os.Bundle;
>>>>>>> d3a5108... Directory Restructure

import com.andela.bark.authentication.GoogleAuth;


public class MainActivity extends Activity {


<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
public class MainActivity extends Activity {
<<<<<<< HEAD

    private FacebookAuth facebookAuth = new FacebookAuth();
=======
>>>>>>> e1dd5ff... App logo placeholder
=======
>>>>>>> 6c14333... Added google api client initialization cade to Main activity
=======
    private GoogleHandler googleHandler;
>>>>>>> 1338636... Refactor GoogleApiClient -> GoogleHandler
=======
    private GoogleAuth googleHandler;
>>>>>>> d3a5108... Directory Restructure

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        facebookAuth.setupFacebookAuth(this);
        facebookAuth.setCallbackManager();
        facebookAuth.trackers();

        setContentView(R.layout.activity_main);
<<<<<<< HEAD
<<<<<<< HEAD

<<<<<<< HEAD
        facebookAuth.loginButton(this);
=======
        // Build GoogleApiClient with access to basic profile
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .addScope(new Scope(Scopes.PLUS_LOGIN))
                .addScope(new Scope(Scopes.PLUS_ME))
                .build();

<<<<<<< HEAD
>>>>>>> 6c14333... Added google api client initialization cade to Main activity
=======
        findViewById(R.id.sign_in_button).setOnClickListener(this);
=======
        googleHandler = new GoogleHandler(this);
>>>>>>> 1338636... Refactor GoogleApiClient -> GoogleHandler
=======
        googleHandler = new GoogleAuth(this);
>>>>>>> d3a5108... Directory Restructure
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
<<<<<<< HEAD
        Log.d("MainActivity", "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);

        if (requestCode == RC_SIGN_IN) {
            // If the error resolution was not successful we should not resolve further.
            if (resultCode != RESULT_OK) {
                mShouldResolve = false;
            }

            mIsResolving = false;
            mGoogleApiClient.connect();
        }
>>>>>>> 0f33e59... Google sign in achieved
    }
    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        facebookAuth.onActivityResult(requestCode, resultCode, data);
=======
        googleHandler.onActivityResult(requestCode, resultCode, data);
>>>>>>> 1338636... Refactor GoogleApiClient -> GoogleHandler
    }
<<<<<<< HEAD
}
=======

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
>>>>>>> 6c14333... Added google api client initialization cade to Main activity
