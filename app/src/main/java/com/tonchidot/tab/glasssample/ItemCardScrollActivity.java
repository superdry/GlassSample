package com.tonchidot.tab.glasssample;

import com.tonchidot.tab.glasssample.model.Item;

import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class ItemCardScrollActivity extends FragmentActivity {
    private ArrayList<Item> mList;

    private final static String TAG_ITEM_CARD_SCROLL = ItemCardScrollFragment.class.getName();

    private ItemCardScrollFragment itemCardScrollFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mList  = intent.getParcelableArrayListExtra("ITEM_LIST");
        setContentView(R.layout.item_card_scroll_activity);

        itemCardScrollFragment = (ItemCardScrollFragment) getSupportFragmentManager().findFragmentByTag(TAG_ITEM_CARD_SCROLL);
        if (itemCardScrollFragment == null)
            itemCardScrollFragment = ItemCardScrollFragment.newInstance(mList);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, itemCardScrollFragment, TAG_ITEM_CARD_SCROLL).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_card_scroll_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.show_detail_menu:
                intent = new Intent(this, ItemDetailActivity.class);
                intent.putExtra("ITEM", mList.get(itemCardScrollFragment.getPosition()));
                startActivity(intent);
                return true;
            case R.id.navi_item_menu:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("google.navigation:q=" + Config.DEMO_LAT + "," + Config.DEMO_LON + "&mode=w"));
                startActivity(intent);
                return true;
            case R.id.stop_app_menu:
                stopService(new Intent(this, LiveCardService.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            openOptionsMenu();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
