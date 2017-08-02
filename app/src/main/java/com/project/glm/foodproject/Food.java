package com.project.glm.foodproject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by glm on 01/08/2017.
 */

public class Food implements Parcelable {
    private final String m_id;
    private final double m_price;
    private final int m_picId;

    public Food(String _id, double _price, int _picId) {
        m_id = _id;
        m_price = _price;
        m_picId = _picId;
    }

    public String getId() {
        return m_id;
    }

    public double getPrice() {
        return m_price;
    }

    public int getPicId() {
        return m_picId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel _dest, int _flags) {
        _dest.writeString(m_id);
        _dest.writeDouble(m_price);
        _dest.writeInt(m_picId);
    }

    public static final Parcelable.Creator<Food> CREATOR = new Parcelable.Creator<Food>() {
        public Food createFromParcel(Parcel _in) {
            return new Food(_in);
        }

        public Food[] newArray(int _size) {
            return new Food[_size];
        }
    };

    private Food(Parcel _in) {
        m_id = _in.readString();
        m_price = _in.readDouble();
        m_picId = _in.readInt();
    }
}
