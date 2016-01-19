package com.tonchidot.tab.glasssample;
import com.tonchidot.tab.glasssample.base.BaseFragmentActivity;
import com.tonchidot.tab.glasssample.model.Item;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class ItemCardScrollActivity extends BaseFragmentActivity {
    private ArrayList<Item> mList;

    private final static String TAG_ITEM_CARD_SCROLL = ItemCardScrollFragment.class.getName();
    private final static String TAG_ITEM_CARD_OVERLAY = ItemCardOverlayFragment.class.getName();

    private ItemCardScrollFragment itemCardScrollFragment;
    private ItemCardOverlayFragment itemCardOverlayFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mList = intent.getParcelableArrayListExtra("ITEM_LIST");
        setContentView(R.layout.item_card_scroll_activity);

        itemCardScrollFragment = (ItemCardScrollFragment) getSupportFragmentManager().findFragmentByTag(TAG_ITEM_CARD_SCROLL);
        if (itemCardScrollFragment == null)
            itemCardScrollFragment = ItemCardScrollFragment.newInstance(mList);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, itemCardScrollFragment, TAG_ITEM_CARD_SCROLL).commit();
        visibleOverlay(mList.get(0));
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

        Item current_item = mList.get(itemCardScrollFragment.getPosition());
        switch (item.getItemId()) {
            case R.id.show_detail_menu:
                intent = new Intent(this, ItemDetailActivity.class);
                intent.putExtra("ITEM", current_item);
                startActivity(intent);
                return true;
            case R.id.navi_item_menu:
                Uri uri = Uri.parse(String.format("google.navigation:q=%f,%f&mode=w",
                                current_item.getPlaceGeo().getLat(),
                                current_item.getPlaceGeo().getLon()
                        ));
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
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

    private void visibleOverlay(Item item) {
        itemCardOverlayFragment = (ItemCardOverlayFragment) getSupportFragmentManager().findFragmentByTag(TAG_ITEM_CARD_OVERLAY);
        if (itemCardOverlayFragment == null)
            itemCardOverlayFragment = ItemCardOverlayFragment.newInstance(item);
        getSupportFragmentManager().beginTransaction().replace(R.id.overlay_fragment_container, itemCardOverlayFragment, TAG_ITEM_CARD_OVERLAY).commit();
    }
}
