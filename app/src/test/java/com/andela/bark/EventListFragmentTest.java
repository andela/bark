package com.andela.bark;

import android.support.v4.app.Fragment;
import android.widget.ListView;

import com.andela.bark.fragments.EventListFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.*;
import static org.assertj.android.api.Assertions.assertThat;

import java.lang.annotation.ElementType;
import java.util.ArrayList;

/**
 * Created by andela-cj on 9/7/15.
 */


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class EventListFragmentTest {
    private Fragment EListFragment;
    private FragmentHostActivity hostActivity;

    @Before
    public void setUp() throws Exception {
       hostActivity = Robolectric.buildActivity(FragmentHostActivity.class).create().get();
    }

    @Test
    public void testFragmentTitle() throws Exception {
        Fragment fragment = hostActivity.getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        assertNotNull(fragment);

        android.support.v4.app.FragmentManager fragmentManager = hostActivity.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();


        //String title = (String) hostActivity.getTitle();
        //assertEquals("Event List", title);
    }


    @After
    public void tearDown() throws Exception {
        hostActivity = null;
    }
}