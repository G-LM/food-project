package com.project.glm.foodproject;

/**
 * Created by glm on 01/08/2017.
 */

public class Food {
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
}
