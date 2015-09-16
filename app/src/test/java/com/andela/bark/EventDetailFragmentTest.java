package com.andela.bark;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Button;

import com.andela.bark.fragments.EventDetailFragment;
import com.andela.bark.fragments.EventListFragment;
import com.andela.bark.fragments.TicketVerificationFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;
import org.robolectric.util.FragmentTestUtil;

import static org.junit.Assert.*;

/**
 * Created by andela-cj on 9/16/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class EventDetailFragmentTest {
    private FragmentHostActivity hostActivity;
    EventDetailFragment fragment;

    @Before
    public void setUp() throws Exception {
        Bundle args = new Bundle();
        args.putString("Event","Event #0");
        hostActivity = Robolectric.buildActivity(FragmentHostActivity.class).create().get();
        fragment = new EventDetailFragment();
        fragment.setArguments(args);

        hostActivity.getFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }


    @Test
    public void testShouldNotBeNull(){
        assertNotNull(fragment);
    }

    @Test
    public void testContainingActivityTitle(){
        assertEquals("Event #0", fragment.getActivity().getTitle());
    }

    @Test
    public void testDashBoardToastMessageOnClick(){
        Button button = (Button)hostActivity.findViewById(R.id.event_dashboard);
        assertTrue(button.hasOnClickListeners());
        button.performClick();
        assertEquals(ShadowToast.getTextOfLatestToast(), "Dashboard Button Clicked");

    }

    @Test
    public void testManageEventButton(){
        TicketVerificationFragment ticket ;
        Button button = (Button) hostActivity.findViewById(R.id.manage_event);
        assertTrue(button.hasOnClickListeners());
        button.performClick();
        ticket = (TicketVerificationFragment)hostActivity.getFragmentManager().findFragmentById(R.id.fragmentContainer);
        assertNotNull(ticket);
    }

    @After
    public void tearDown() throws Exception {

    }
}
