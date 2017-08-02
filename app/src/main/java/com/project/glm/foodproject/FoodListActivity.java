package com.project.glm.foodproject;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by glm on 01/08/2017.
 */

public class FoodListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        final ListView listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food item = (Food) listView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), FoodDetailActivity.class);
                intent.putExtra("FOOD", item);
                startActivity(intent);
            }
        });

        FoodListAdapter adapter = new FoodListAdapter(listView.getContext(), R.layout.food_item, fillList());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private ArrayList<Food> fillList() {
        ArrayList<Food> foodList = new ArrayList<>();
        for(int i=0; i<100; ++i) {
            foodList.add(new Food("Food", 10.00, R.mipmap.ic_launcher));
            foodList.add(new Food("TRUC", 20.00, R.mipmap.ic_launcher));
        }
        return foodList;
    }

}
