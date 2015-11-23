package com.andela.bark.fragments;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Fragment;
import android.widget.TextView;

import com.andela.bark.R;
import com.andela.bark.models.Events;
import com.andela.bark.models.QueryCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andela on 8/31/15.
 */
public class EventListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ArrayAdapter<String> listAdapter;
    private ListView mainListView;
    private List<Events> eventsList;

    private static final String REQUEST_STRING = "Events";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_eventlist, container, false);
        getActivity().setTitle("Events");

        mainListView = (ListView) v.findViewById( R.id.mainListView );
        inflateEventList();

        mainListView.setAdapter(listAdapter);
        mainListView.setOnItemClickListener(this);

        return v;
    }

    private void inflateEventList(){
        final ArrayList<String> events = new ArrayList<String>();
        Events.getAll(new QueryCallback() {
            @Override
            public void onSuccess(List<?> list) {
                eventsList = (List<Events>)list;
                for (Events ent : eventsList) {
                    events.add(ent.getString("Name"));
                }
                listAdapter = new ArrayAdapter<>(getActivity(), R.layout.simplerow, events.toArray(new String[0]));
                mainListView.setAdapter(listAdapter);
            }
            @Override
            public void onError(Exception e) {
                TextView view = (TextView) getActivity().findViewById(R.id.rowTextView);
                mainListView.setEmptyView(view);
                e.printStackTrace();
            }
        });
    }

    public ListView getMainListView() {
        return mainListView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getAdapter().getItem(position).toString();
        Bundle args = new Bundle();
        args.putString("Text", item);
        if (eventsList != null) {
            Events event = eventsList.get(position);
            String name = event.getString("Name");
            String eventId = event.getObjectId();
            args.putString("Event", name);
            args.putString("EventId", eventId);
        }

        EventDetailFragment eventDetailFragment = new EventDetailFragment();
        eventDetailFragment.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, eventDetailFragment)
                .addToBackStack(null)
                .commit();
    }
}