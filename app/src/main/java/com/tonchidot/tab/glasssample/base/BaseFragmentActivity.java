package com.tonchidot.tab.glasssample.base;

import android.support.v4.app.FragmentActivity;

import com.tonchidot.tab.glasssample.BusHolder;

/**
 * Created by superdry on 16/01/12.
 */
public class BaseFragmentActivity extends FragmentActivity {

    @Override
    protected void onResume() {
        super.onResume();
        BusHolder.BUS.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusHolder.BUS.unregister(this);
    }
}
