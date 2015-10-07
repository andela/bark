package com.andela.bark;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.andela.bark.fragments.EventListFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.util.FragmentTestUtil;

import static junit.framework.Assert.*;
import static org.assertj.android.api.Assertions.assertThat;
import static org.robolectric.util.FragmentTestUtil.startFragment;

/**
 * Created by andela-cj on 9/7/15.
 */


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class EventListFragmentTest {
    private FragmentHostActivity hostActivity;
    private EventListFragment fragment;

    @Before
    public void setUp() throws Exception {
       hostActivity = Robolectric.buildActivity(FragmentHostActivity.class).create().get();
        fragment = (EventListFragment) hostActivity.getFragmentManager().findFragmentById(R.id.fragmentContainer);
        FragmentTestUtil.startFragment(fragment);
    }

    @Test
    public void testLisAdapter() throws Exception {
        assertNotNull(fragment);
        setListAdapter();
        assertEquals(2, fragment.getMainListView().getCount());
        assertTrue(fragment.isAdded());

    }

    public void setListAdapter(){
        String list[] = {"Event #0","Event#2"};
        ListAdapter listAdapter = new ArrayAdapter<String>(hostActivity, R.layout.simplerow,list);
        fragment.getMainListView().setAdapter(listAdapter);
    }

    @Test
    public void testOnClickListener(){
        assertTrue(fragment.getMainListView().getOnItemClickListener() != null);
    }

    @Test
    public void testTransitionOnItemClick(){
        setListAdapter();
        Shadows.shadowOf(fragment.getMainListView()).performItemClick(0);
        assertFalse(fragment.isAdded());
    }


    @After
    public void tearDown() throws Exception {
        hostActivity = null;
    }
}