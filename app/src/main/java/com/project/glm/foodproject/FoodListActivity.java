package com.project.glm.foodproject;

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
        for(int i=0; i<10; ++i) {
            foodList.add(new Food("Tajine aubergine agneau", 20.00,
                    R.drawable.tajine_aubergine_agneau, R.drawable.tajine_aubergine_agneau_mini, "Decription : Tajine aubergine agneau"));
            foodList.add(new Food("Salade lentille grenade", 15.00,
                    R.drawable.salade_lentille_grenade, R.drawable.salade_lentille_grenade_mini, "Descrption : Salade lentille grenade" +
                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nFelicitations ! Vous êtes a la fin de la description! "));
        }
        return foodList;
    }

}
