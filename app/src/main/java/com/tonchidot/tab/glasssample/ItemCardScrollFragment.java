package com.tonchidot.tab.glasssample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.android.glass.widget.CardScrollView;
import com.squareup.otto.Bus;
import com.tonchidot.tab.glasssample.base.BaseFragment;
import com.tonchidot.tab.glasssample.event.ItemChangedEvent;
import com.tonchidot.tab.glasssample.model.Item;

import java.util.ArrayList;

/**
 * Created by superdry on 16/01/12.
 */
public class ItemCardScrollFragment extends BaseFragment {

    private ArrayList<Item> mList;
    private CardScrollView mCardScrollView;
    private ItemCardScrollAdapter mAdapter;

    public static ItemCardScrollFragment newInstance(ArrayList<Item> items) {
        ItemCardScrollFragment fragment = new ItemCardScrollFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("ITEM_LIST", items);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mList = getArguments().getParcelableArrayList("ITEM_LIST");

        mCardScrollView = new CardScrollView(getActivity());
        mAdapter = new ItemCardScrollAdapter();
        mAdapter.setItems(getActivity(), mList);
        mCardScrollView.setAdapter(mAdapter);
        mCardScrollView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BusHolder.BUS.post(new ItemChangedEvent(mAdapter.getItemObject(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mCardScrollView.activate();
        return mCardScrollView;
    }

    public int getPosition() {
        return mCardScrollView.getSelectedItemPosition();
    }
}
