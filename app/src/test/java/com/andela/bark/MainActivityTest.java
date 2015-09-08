//package com.andela.bark;
//
//import android.widget.ImageView;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.robolectric.Robolectric;
//import org.robolectric.RobolectricGradleTestRunner;
//import org.robolectric.annotation.Config;
//import static junit.framework.Assert.*;
//import static org.assertj.android.api.Assertions.assertThat;
//
//
//
///**
// * Created by andela-cj on 8/26/15.
// */
//
//
//@RunWith(RobolectricGradleTestRunner.class)
//@Config(constants = BuildConfig.class, sdk = 21)
//public class MainActivityTest {
//    private MainActivity landingActivity;
//
//    @Before
//    public void setUp() throws Exception {
//        landingActivity = Robolectric.buildActivity(MainActivity.class).create().get();
//    }
//
//
//
////    @Test
////    public void titleIsCorrect()  throws Exception{
////        Activity activity = Robolectric.setupActivity(MainActivity.class);
////        assertTrue(activity.getTitle().toString().equals("Gatekeepr"));
////    }
//
//    @Test
//    public void testImageViewContainsImage() throws Exception{
//        ImageView appLogo =  (ImageView) landingActivity.findViewById(R.id.applogoImageView);
//        assertThat(appLogo).isNotNull();
//        assertThat(appLogo.getDrawable()).isNotNull();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//
//    }
//
//
//}
