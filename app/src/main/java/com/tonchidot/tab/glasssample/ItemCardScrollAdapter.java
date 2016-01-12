package com.tonchidot.tab.glasssample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;
import com.tonchidot.tab.glasssample.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by superdry on 16/01/12.
 */
public class ItemCardScrollAdapter extends CardScrollAdapter {

    private List<CardBuilder> mCards;
    private List<Item> mList;


    public void setItems(Context context, List<Item> items){
        mList = items;
        mCards = new ArrayList<>();
        for (Item item : items) {
            mCards.add(new CardBuilder(context, CardBuilder.Layout.CAPTION)
                    .setText(item.getTitle())
                    .setFootnote(item.getDescription())
                    .addImage(item.getImageId()).setAttributionIcon(R.drawable.statusbar_icon));
        }
    }

    @Override
    public int getPosition(Object item) {
        return mCards.indexOf(item);
    }

    @Override
    public int getCount() {
        return mCards.size();
    }

    @Override
    public CardBuilder getItem(int position) {
        return mCards.get(position);
    }

    public Item getItemObject(int position) {
        return mList.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return CardBuilder.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position){
        return mCards.get(position).getItemViewType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return mCards.get(position).getView(convertView, parent);
    }
}