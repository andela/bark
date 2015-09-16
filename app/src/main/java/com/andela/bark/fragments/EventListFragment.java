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
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import com.andela.bark.models.Event;

/**
 * Created by andela on 8/31/15.
 */
public class EventListFragment extends Fragment {
    private ArrayAdapter<String> listAdapter;
    private ListView mainListView;
    private List<ParseObject> object;

    private static final String REQUEST_STRING = "Events";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_eventlist, container, false);
        getActivity().setTitle("Event List");

        mainListView = (ListView) v.findViewById( R.id.mainListView );
        inflateEventList();
        mainListView.setAdapter(listAdapter);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getAdapter().getItem(position).toString();
                Bundle args = new Bundle();
                args.putString("Event", item);
                if (object != null) {
                    ParseObject obj = object.get(position);
                    args.putString("Event", obj.getString("Name"));
                    args.putString("EventId", obj.getObjectId());
                }

                EventDetailFragment eventDetailFragment = new EventDetailFragment();
                eventDetailFragment.setArguments(args);
                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, eventDetailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return v;
    }

    private void inflateEventList(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(REQUEST_STRING);
        final ArrayList<String> events = new ArrayList<String>();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (list != null) {
                    object = list;
                    for (ParseObject ob : list) {
                        Event event = new Event();
                        event.setName(ob.getString("Name"));
                        event.setLocation(ob.getString("location"));
                        event.setId(ob.getObjectId());
                        events.add(event.getName());
                    }
                    listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.simplerow, events);
                    mainListView.setAdapter(listAdapter);
                }
            }
        });
        if (events.size() == 0){
            TextView view = (TextView) getActivity().findViewById(R.id.rowTextView);
            mainListView.setEmptyView(view);
        }
    }

    public ListView getMainListView() {
        return mainListView;
    }

}