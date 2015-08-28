package com.andela.bark;

import android.app.Activity;
<<<<<<< HEAD
<<<<<<< HEAD
import android.content.Intent;
=======
=======
import android.content.Intent;
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
    /* Is there a ConnectionResult resolution in progress? */
    private boolean mIsResolving = false;

    /* Should we automatically resolve ConnectionResults when possible? */
    private boolean mShouldResolve = false;

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

<<<<<<< HEAD
>>>>>>> 6c14333... Added google api client initialization cade to Main activity
=======
        findViewById(R.id.sign_in_button).setOnClickListener(this);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
        // onConnected indicates that an account was selected on the device, that the selected
        // account has granted any requested permissions to our app and that we were able to
        // establish a service connection to Google Play services.
        Log.d("MainActivity", "onConnected:" + bundle);
        mShouldResolve = false;

        // Show the signed-in UI
        Toast.makeText(this, "signed-in ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // Could not connect to Google Play Services.  The user needs to select an account,
        // grant permissions or resolve an error in order to sign in. Refer to the javadoc for
        // ConnectionResult to see possible error codes.
        Log.d("MainActivity", "onConnectionFailed:" + connectionResult);

        if (!mIsResolving && mShouldResolve) {
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    Log.e("MainActivity", "Could not resolve ConnectionResult.", e);
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }
            } else {
                // Could not resolve the connection result, show the user an
                // error dialog.
                Toast.makeText(this, "Connection could not be resolved", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Show the signed-out UI
            Toast.makeText(this, "Signed-out", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sign_in_button) {
            onSignInClicked();
        }
    }

    private void onSignInClicked() {
        // User clicked the sign-in button, so begin the sign-in process and automatically
        // attempt to resolve any errors that occur.
        mShouldResolve = true;
        mGoogleApiClient.connect();

        // Show a message to the user that we are signing in.
//        mStatusTextView.setText(R.string.signing_in);
        Toast.makeText(this, "Signing in", Toast.LENGTH_LONG).show();

    }
}
>>>>>>> 6c14333... Added google api client initialization cade to Main activity
