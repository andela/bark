package com.andela.bark;

import android.app.Activity;
<<<<<<< HEAD
import android.content.Intent;
=======
import android.support.v7.app.ActionBarActivity;
>>>>>>> e1dd5ff... App logo placeholder
import android.os.Bundle;


public class MainActivity extends Activity {
<<<<<<< HEAD

    private FacebookAuth facebookAuth = new FacebookAuth();
=======
>>>>>>> e1dd5ff... App logo placeholder

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        facebookAuth.setupFacebookAuth(this);
        facebookAuth.setCallbackManager();
        facebookAuth.trackers();

        setContentView(R.layout.activity_main);

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
    }
}