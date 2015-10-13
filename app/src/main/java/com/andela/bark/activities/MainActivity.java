package com.andela.bark.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.andela.bark.R;
import com.andela.bark.authentication.FacebookAuth;
import com.andela.bark.authentication.GoogleAuth;
import com.andela.bark.models.Privilege;
import com.andela.bark.models.User;
import com.facebook.Profile;
import com.facebook.login.widget.LoginButton;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;


public class MainActivity extends Activity {
    private FacebookAuth fba = new FacebookAuth(this);
    private GoogleAuth googleHandler;
    private Drawable drawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fba.stuffInsideoncreate();
        try {
            ParseObject.registerSubclass(Privilege.class);
            ParseObject.registerSubclass(User.class);
            Parse.initialize(this, "vKYBj5ToX5nVxINd0ubtBqoRo3EyHB5jcNLS7rNw", "zFYifD7N4dHLHFZ7Js05rOrhWdnl085RJSSrFK8W");
            ParseInstallation.getCurrentInstallation().saveInBackground();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);
        googleHandler = new GoogleAuth(this);

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
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        googleHandler.onActivityResult(requestCode, resultCode, data);
        fba.getmCallbackManager().onActivityResult(requestCode, resultCode, data);
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
        fba.getTracker().stopTracking();
        fba.getProfileTracker().stopTracking();
        fba.logOut();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}