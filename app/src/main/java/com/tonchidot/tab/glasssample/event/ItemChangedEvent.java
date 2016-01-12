package com.tonchidot.tab.glasssample.event;

import com.tonchidot.tab.glasssample.model.Item;

import lombok.Getter;

/**
 * Created by superdry on 16/01/12.
 */
public class ItemChangedEvent {
    @Getter
    final Item item;

    public ItemChangedEvent(Item item) {
        this.item = item;
    }
}
