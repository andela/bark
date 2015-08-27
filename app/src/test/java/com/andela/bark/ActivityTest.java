package com.andela.bark;

/**
 * Created by andela-cj on 8/26/15.
 */
import android.widget.ImageView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
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
    public void testLandingPageContainApplogo(){
        ImageView applogo =  (ImageView) landingActivity.findViewById(R.id.applogoImageView);
        assertThat(applogo).isNotNull();
    }

    @After
    public void tearDown() throws Exception {


    }
}
