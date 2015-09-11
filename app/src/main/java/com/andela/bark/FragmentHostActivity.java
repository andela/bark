package com.andela.bark;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.app.FragmentManager;

import com.andela.bark.fragments.EventListFragment;

/**
 * Created by andela-jugba on 8/31/15.
 */
public class FragmentHostActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentcontainer);
        setUpFragment();
    }

    private void setUpFragment(){
        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null){
            fragment = new EventListFragment();
            fm.beginTransaction().add(R.id.fragmentContainer,fragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}