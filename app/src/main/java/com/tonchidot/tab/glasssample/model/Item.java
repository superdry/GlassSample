package com.tonchidot.tab.glasssample.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

/**
 * Created by superdry on 16/01/08.
 */
@Data
public class Item implements Parcelable {

    PlaceGeo placeGeo;
    String description;
    String title;
    int imageId;
    String placeName;
    String address;

    public Item(){

    }

    protected Item(Parcel in) {
        placeGeo = (PlaceGeo) in.readValue(PlaceGeo.class.getClassLoader());
        description = in.readString();
        title = in.readString();
        imageId = in.readInt();
        placeName = in.readString();
        address = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(placeGeo);
        dest.writeString(description);
        dest.writeString(title);
        dest.writeInt(imageId);
        dest.writeString(placeName);
        dest.writeString(address);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Data
    public static class PlaceGeo implements Parcelable {
        float lat;
        float lon;

        public PlaceGeo(){

        }

        protected PlaceGeo(Parcel in) {
            lat = in.readFloat();
            lon = in.readFloat();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeFloat(lat);
            dest.writeFloat(lon);
        }

        public static final Parcelable.Creator<PlaceGeo> CREATOR = new Parcelable.Creator<PlaceGeo>() {
            @Override
            public PlaceGeo createFromParcel(Parcel in) {
                return new PlaceGeo(in);
            }

            @Override
            public PlaceGeo[] newArray(int size) {
                return new PlaceGeo[size];
            }
        };
    }
}
