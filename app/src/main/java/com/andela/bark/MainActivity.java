package com.andela.bark;

import android.app.Activity;
<<<<<<< HEAD
import android.content.Intent;
=======
import android.support.v7.app.ActionBarActivity;
>>>>>>> e1dd5ff... App logo placeholder
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
>>>>>>> 6c14333... Added google api client initialization cade to Main activity

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.plus.Plus;


public class MainActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;

    /* Client used to interact with Google APIs. */
    private GoogleApiClient mGoogleApiClient;

    @Override
    public void onClick(View v) {

    }

<<<<<<< HEAD
public class MainActivity extends Activity {
<<<<<<< HEAD

    private FacebookAuth facebookAuth = new FacebookAuth();
=======
>>>>>>> e1dd5ff... App logo placeholder
=======
>>>>>>> 6c14333... Added google api client initialization cade to Main activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        facebookAuth.setupFacebookAuth(this);
        facebookAuth.setCallbackManager();
        facebookAuth.trackers();

        setContentView(R.layout.activity_main);

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

>>>>>>> 6c14333... Added google api client initialization cade to Main activity
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        facebookAuth.onActivityResult(requestCode, resultCode, data);
    }
<<<<<<< HEAD
}
=======

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
>>>>>>> 6c14333... Added google api client initialization cade to Main activity
