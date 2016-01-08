package com.tonchidot.tab.glasssample;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.widget.CardScrollView;
import com.tonchidot.tab.glasssample.model.Item;

import java.util.ArrayList;

/**
 * Created by superdry on 16/01/12.
 */
public class ItemCardScrollFragment extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mList = getArguments().getParcelableArrayList("ITEM_LIST");

        mCardScrollView = new CardScrollView(getActivity());
        mAdapter = new ItemCardScrollAdapter();
        mAdapter.setItems(getActivity(), mList);
        mCardScrollView.setAdapter(mAdapter);
        mCardScrollView.activate();
        return mCardScrollView;
    }

    public int getPosition() {
        return mCardScrollView.getSelectedItemPosition();
    }

}
