package com.project.glm.foodproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class FoodDetailActivity extends AppCompatActivity {
    public static final String URL = "http://demo5337554.mockable.io";
    private Food m_foodItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        Intent i = getIntent();
        m_foodItem = (Food) i.getParcelableExtra("FOOD");

        TextView name = (TextView) findViewById(R.id.TV_detail_food_name);
        name.setText(m_foodItem.getId());
        TextView price = (TextView) findViewById(R.id.TV_detail_food_price);
        price.setText(Common.doubleToPrice(m_foodItem.getPrice()));
        ImageView pic = (ImageView) findViewById(R.id.IV_detail_food_pic);
        pic.setImageResource(m_foodItem.getPicId());
        TextView description = (TextView) findViewById(R.id.TV_detail_food_description);
        description.setText(m_foodItem.getDescription());
    }

    public void onOrderClicked(View _view) {
        try {
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("FOOD_ID", m_foodItem.getId());

            Common.ProcessRequest process = new Common.ProcessRequest();
            process.execute(URL, jsonParam);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Merci!", Toast.LENGTH_LONG).show();
    }

}
