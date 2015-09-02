package com.andela.bark.authentication;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.andela.bark.EventListFragmentContainerActivity;
import com.andela.bark.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;

/**
 * Created by andela-cj on 8/31/15.
 */
public class GoogleAuth implements
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

    private Activity myActivity;

    public GoogleAuth(Activity activity){

        myActivity = activity;
        // Build GoogleApiClient with access to basic profile
        mGoogleApiClient = new GoogleApiClient.Builder(activity)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();


        activity.findViewById(R.id.sign_in_button).setOnClickListener(this);
    }


    @Override
    public void onConnected(Bundle bundle) {
        // onConnected indicates that an account was selected on the device, that the selected
        // account has granted any requested permissions to our app and that we were able to
        // establish a service connection to Google Play services.
        Log.d("MainActivity", "onConnected:" + bundle);
        mShouldResolve = false;

        // Show the signed-in UI
        Toast.makeText(myActivity, "signed-in ", Toast.LENGTH_SHORT).show();

//        // Start fragment activity on sign-in
//        Intent i  = new Intent(myActivity, FragmentContainerActivity.class);
//        myActivity.startActivity(i);
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
                    connectionResult.startResolutionForResult(myActivity, RC_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    Log.e("MainActivity", "Could not resolve ConnectionResult.", e);
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }
            } else {
                // Could not resolve the connection result, show the user an
                // error dialog.
                Toast.makeText(myActivity, "Connection could not be resolved", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Show the signed-out UI
            Toast.makeText(myActivity, "Signed-out", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sign_in_button) {
            Intent i  = new Intent(myActivity, EventListFragmentContainerActivity.class);
            myActivity.startActivity(i);
//            onSignInClicked();
        }
    }

    private void onSignInClicked() {
        // User clicked the sign-in button, so begin the sign-in process and automatically
        // attempt to resolve any errors that occur.
        mShouldResolve = true;
        mGoogleApiClient.connect();
        // Show a message to the user that we are signing in.
//        mStatusTextView.setText(R.string.signing_in);
        Toast.makeText(myActivity, "Signing in", Toast.LENGTH_LONG).show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("MainActivity", "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);

        if (requestCode == RC_SIGN_IN) {
            // If the error resolution was not successful we should not resolve further.
            if (resultCode != myActivity.RESULT_OK) { mShouldResolve = false; }

            mIsResolving = false;
            mGoogleApiClient.connect();
        }
    }

    public void connect(){
        mGoogleApiClient.connect();
    }

<<<<<<< HEAD
    public void disconnect() {
        if (mGoogleApiClient.isConnected()){
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient);
         }
=======
    public void disconnect(){
//        Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
//        Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient);
>>>>>>> 24b864e... list
        mGoogleApiClient.disconnect();
    }
}
