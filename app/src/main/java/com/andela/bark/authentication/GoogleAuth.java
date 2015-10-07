package com.andela.bark.authentication;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.andela.bark.FragmentHostActivity;
import com.andela.bark.GKprManger;
import com.andela.bark.MainActivity;
import com.andela.bark.R;
import com.andela.bark.models.User;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

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

    private Activity myActivity;

    public GoogleAuth(Activity activity){

        myActivity = activity;
        mGoogleApiClient = new GoogleApiClient.Builder(myActivity)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();

        activity.findViewById(R.id.sign_in_button).setOnClickListener(this);
        ((SignInButton)activity.findViewById(R.id.sign_in_button)).setSize(SignInButton.SIZE_WIDE);
    }


    @Override
    public void onConnected(Bundle bundle) {
        mShouldResolve = false;
        this.person = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
        if (person != null){
            User user = User.createGoogleUser(person);
            GKprManger kprManger = new GKprManger(user, myActivity);
            if(kprManger.isAuthenticated){
                Toast.makeText(myActivity, "signed-in ", Toast.LENGTH_SHORT).show();
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
                    Log.e("MainActivity", "Could not resolve ConnectionResult.", e);
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }
            } else {
                Toast.makeText(myActivity, "Connection could not be resolved", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(myActivity, "Signed-out", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sign_in_button) {

            onSignInClicked();
        }
    }

    private void onSignInClicked() {

        mShouldResolve = true;
        mGoogleApiClient.connect();
        Toast.makeText(myActivity, "Signing in", Toast.LENGTH_LONG).show();
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

    public void disconnect(){
        if (mGoogleApiClient.isConnected())
            Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient);
             mGoogleApiClient.disconnect();
    }

}