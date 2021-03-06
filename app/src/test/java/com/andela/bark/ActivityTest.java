package com.andela.bark;

/**
 * Created by andela-cj on 8/26/15.
 */
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.andela.bark.authentication.FacebookAuth;
import com.andela.bark.authentication.GoogleAuth;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import  static org.junit.Assert.*;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.assertj.android.api.Assertions.assertThat;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ActivityTest {

    private MainActivity landingActivity;
    @Before
    public void setUp() throws Exception {
        landingActivity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void testImageViewContainsImage(){
        ImageView applogo =  (ImageView) landingActivity.findViewById(R.id.applogoImageView);
        assertThat(applogo).isNotNull();
        assertThat(applogo).isVisible();
        Drawable logo = RuntimeEnvironment.application
                .getApplicationContext()
                .getResources().getDrawable(R.drawable.gatekeeper);
        assertThat(applogo.getDrawable()).isEqualTo(logo);
    }

    @Test
    public void testGoggleAuth(){
        GoogleAuth auth = new GoogleAuth(landingActivity);
        assertNotNull(auth);
    }

    @Test
    public void testFacebookAuth(){
        FacebookAuth fbAuth = new FacebookAuth(landingActivity);
        assertNotNull(fbAuth);
    }

    @After
    public void tearDown() throws Exception {


    }
}
