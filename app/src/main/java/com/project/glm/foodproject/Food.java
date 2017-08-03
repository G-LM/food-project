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
    private final int m_previewPicId;
    private final String m_description;

    public Food(String _id, double _price, int _picId, int _previewPicId, String _description) {
        m_id = _id;
        m_price = _price;
        m_picId = _picId;
        m_previewPicId = _previewPicId;
        m_description  =_description;
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

    public int getPreviewPicId() {
        return m_previewPicId;
    }

    public String getDescription() {
        return m_description;
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
        _dest.writeInt(m_previewPicId);
        _dest.writeString(m_description);
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
        m_previewPicId = _in.readInt();
        m_description = _in.readString();
    }
}
