package com.andela.bark;

import com.andela.bark.fragments.EventListFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * Created by andela-cj on 9/16/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class FragmentHostActivityTest {

    private FragmentHostActivity hostActivity;
    @Before
    public void setUp() throws Exception {
        hostActivity = Robolectric.buildActivity(FragmentHostActivity.class).create().start().get();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testShouldNotBeNull(){
        assertNotNull(hostActivity);
    }
    @Test
    public void TestShouldContainFragment(){
        EventListFragment eventListFragment = (EventListFragment)hostActivity.getFragmentManager().findFragmentById(R.id.fragmentContainer);
        assertNotNull(eventListFragment);
    }
}
