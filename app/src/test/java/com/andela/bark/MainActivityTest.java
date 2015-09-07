package com.andela.bark;

/**
 * Created by andela-cj on 8/26/15.
 */
import android.app.Activity;
import android.widget.ImageView;

import com.andela.bark.authentication.GoogleAuth;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import  static org.junit.Assert.*;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertTrue;
import static org.assertj.android.api.Assertions.assertThat;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {
    private MainActivity landingActivity;

    @Before
    public void setUp() throws Exception {
        landingActivity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void titleIsCorrect()  throws Exception{
        Activity activity = Robolectric.setupActivity(MainActivity.class);
        assertTrue(activity.getTitle().toString().equals("Gatekeepr"));
    }

    @Test
    public void testImageViewContainsImage(){
        ImageView applogo =  (ImageView) landingActivity.findViewById(R.id.applogoImageView);
        assertThat(applogo).isNotNull();
        assertThat(applogo.getDrawable()).isNotNull();
    }

    @Test
    public void testGoggleAuth(){
        GoogleAuth auth = new GoogleAuth(landingActivity);
        assertNotNull(auth);
    }


}
