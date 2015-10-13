package com.andela.bark.authentication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.andela.bark.activities.FragmentHostActivity;
import com.andela.bark.GateKeeperManager;
import com.andela.bark.R;
import com.andela.bark.models.User;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

/**
 * Created by andela-cj on 8/31/15.
 */
public class GoogleAuth implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener{

    private static final int RC_SIGN_IN = 0;
    private GoogleApiClient mGoogleApiClient;
    private boolean mIsResolving = false;
    private boolean mShouldResolve = false;
    private Person person;
    ProgressDialog progress;

    private Activity myActivity;

    public GoogleAuth(Activity activity){

        myActivity = activity;
        mGoogleApiClient = new GoogleApiClient.Builder(myActivity)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();


        myActivity.findViewById(R.id.sign_in_button).setOnClickListener(this);
        activity.findViewById(R.id.sign_in_button).setOnClickListener(this);
        ((SignInButton)activity.findViewById(R.id.sign_in_button)).setSize(SignInButton.SIZE_WIDE);
    }


    @Override
    public void onConnected(Bundle bundle) {
        mShouldResolve = false;
        this.person = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
        if (progress!= null) progress.dismiss();
        if (person != null){
            User user = User.createGoogleUser(person);
            GateKeeperManager kprManger = new GateKeeperManager(user, myActivity);
            if(kprManger.isAuthenticated){
                Intent i  = new Intent(myActivity, FragmentHostActivity.class);
                myActivity.startActivity(i);
            }
        }
    }


    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        if (!mIsResolving && mShouldResolve) {
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(myActivity, RC_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    Toast.makeText(myActivity, "Connection could not be resolved", Toast.LENGTH_SHORT).show();
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }
            } else {
                if (progress!= null && progress.isShowing()) progress.dismiss();
                myActivity.findViewById(R.id.sign_in_button).setEnabled(true);
            }
        } else {
            if (progress!= null && progress.isShowing()) progress.dismiss();
            myActivity.findViewById(R.id.sign_in_button).setEnabled(true);

        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sign_in_button) {
            progress = new  ProgressDialog (myActivity);
            progress.setCancelable(false);
            progress.setMessage("Loading...");
            progress.show();
            v.setEnabled(false);
            onSignInClicked();
        }
    }

    private void onSignInClicked() {

        mShouldResolve = true;
        mGoogleApiClient.connect();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RC_SIGN_IN) {
            if (resultCode != Activity.RESULT_OK) {
                mShouldResolve = false;
            }

            mIsResolving = false;
            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }
    }

    public void connect(){
        mGoogleApiClient.connect();
    }

    public void disconnect(){
        if (mGoogleApiClient.isConnected())
            Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient);
        myActivity.findViewById(R.id.sign_in_button).setEnabled(true);
        mGoogleApiClient.disconnect();
    }

}