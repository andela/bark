package com.andela.bark;

import android.app.Fragment;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.andela.bark.fragments.EventDetailFragment;
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
        EventListFragment fragment =(EventListFragment) hostActivity.getFragmentManager().findFragmentById(R.id.fragmentContainer);
        FragmentTestUtil.startFragment(fragment);
        assertNotNull(fragment);

        String list[] = {"Event #0","Event#2"};
        ListAdapter listAdapter = new ArrayAdapter<String>(hostActivity, R.layout.simplerow,list);
        fragment.getMainListView().setAdapter(listAdapter);
        assertEquals(2, fragment.getMainListView().getCount());
        Shadows.shadowOf(fragment.getMainListView()).performItemClick(1);
        assertEquals(hostActivity.getTitle(),"Event #2");
//        assertThat
//        Fragment detailFragment = hostActivity.getFragmentManager().findFragmentById(R.id.fragmentContainer);
//        assertThat(detailFragment).isNotNull();


    }


    @After
    public void tearDown() throws Exception {
        hostActivity = null;
    }
}