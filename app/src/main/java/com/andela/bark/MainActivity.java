package com.andela.bark;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.andela.bark.authentication.GoogleAuth;


public class MainActivity extends Activity {


    private GoogleAuth googleHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        googleHandler = new GoogleAuth(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
