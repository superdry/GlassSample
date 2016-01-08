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
        card.setHeading(item.getTitle());
        card.setIcon(item.getImageId());
        card.setSubheading(item.getPlaceName());
        card.setFootnote("tab");
        card.setTimestamp("just now");
        return card.getView();
    }

}
