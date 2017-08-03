package com.project.glm.foodproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by glm on 02/08/2017.
 */

public class FoodListAdapter extends ArrayAdapter<Food> {

    private Activity m_activity;
    private ArrayList<Food> m_foodList;
    private LayoutInflater m_layoutInflater;

    public FoodListAdapter (Context _ctx, int _ressource, ArrayList<Food> _foodList) {
        super(_ctx, _ressource, _foodList);
        m_foodList = _foodList;
        m_layoutInflater = LayoutInflater.from(_ctx);
    }

    public Food getItem(int position){
        return m_foodList.get(position);
    }

    public static class ViewHolder {
        public TextView display_name;
        public TextView display_price;
        public ImageView display_picture;
    }

    @Override
    public View getView(int _position, View _view, ViewGroup _parent) {
        final ViewHolder holder;
        if (_view == null) {
            _view = m_layoutInflater.inflate(R.layout.food_item, null);
            holder = new ViewHolder();
            holder.display_name = (TextView) _view.findViewById(R.id.TV_food_name);
            holder.display_price = (TextView) _view.findViewById(R.id.TV_food_price);
            holder.display_picture = (ImageView) _view.findViewById(R.id.IV_food_pic);
            _view.setTag(holder);
        } else {
            holder = (ViewHolder) _view.getTag();
        }

        holder.display_name.setText(m_foodList.get(_position).getId());
        holder.display_price.setText(Common.doubleToPrice(m_foodList.get(_position).getPrice()));
        holder.display_picture.setImageResource(m_foodList.get(_position).getPicId());

        return _view;
    }

}