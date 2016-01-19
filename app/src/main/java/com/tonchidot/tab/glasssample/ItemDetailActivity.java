package com.tonchidot.tab.glasssample;

import com.google.android.glass.widget.CardBuilder;
import com.tonchidot.tab.glasssample.model.Item;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;


public class ItemDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        Item item = getIntent().getParcelableExtra("ITEM");

        setContentView(buildView(item));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Builds a Glass styled "Hello World!" view using the {@link CardBuilder} class.
     */
    private View buildView(Item item) {
        CardBuilder card = new CardBuilder(this, CardBuilder.Layout.AUTHOR);
        card.setText(item.getDescription());
        card.setHeading(item.getPlaceName());
        card.setSubheading(item.getAddress());
        card.setIcon(item.getImageId());
        card.setFootnote(item.getTitle());
        card.setAttributionIcon(R.drawable.statusbar_icon);
        card.setTimestamp(R.string.justnow);
        return card.getView();
    }

}
