package com.andela.bark.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.andela.bark.R;
import com.andela.bark.authentication.FacebookAuth;
import com.andela.bark.authentication.GoogleAuth;
import com.facebook.Profile;
import com.facebook.login.widget.LoginButton;


public class MainActivity extends Activity {
    private FacebookAuth facebookAuth = new FacebookAuth(this);
    private GoogleAuth googleHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookAuth.stuffInsideoncreate();
        setContentView(R.layout.activity_main);
        googleHandler = new GoogleAuth(this);

        LoginButton loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.registerCallback(facebookAuth.getmCallbackManager(), facebookAuth.getmCallback());
    }


    @Override
    public void onResume(){
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        if(facebookAuth.getProfile() != null){
            Intent intent = new Intent(this, FragmentHostActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        googleHandler.onActivityResult(requestCode, resultCode, data);
        facebookAuth.getmCallbackManager().onActivityResult(requestCode, resultCode, data);
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
        facebookAuth.getTracker().stopTracking();
        facebookAuth.getProfileTracker().stopTracking();
        facebookAuth.logOut();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}