package com.tonchidot.tab.glasssample.base;

import android.support.v4.app.Fragment;

import com.tonchidot.tab.glasssample.BusHolder;

/**
 * Created by superdry on 16/01/12.
 */
public class BaseFragment extends Fragment {

    @Override
    public void onResume() {
        super.onResume();
        BusHolder.BUS.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusHolder.BUS.unregister(this);
    }
}

