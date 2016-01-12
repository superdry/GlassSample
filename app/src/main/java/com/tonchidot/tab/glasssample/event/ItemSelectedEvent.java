package com.tonchidot.tab.glasssample.event;

import com.tonchidot.tab.glasssample.model.Item;

import lombok.Getter;

/**
 * Created by superdry on 16/01/12.
 */

public class ItemSelectedEvent {
    @Getter
    final Item item;

    public ItemSelectedEvent(Item item) {
        this.item = item;
    }
}
