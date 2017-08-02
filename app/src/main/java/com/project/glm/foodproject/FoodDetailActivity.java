package com.project.glm.foodproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class FoodDetailActivity extends AppCompatActivity {
    public static final String URL = "http://demo5337554.mockable.io";
    private Food m_foodItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        Intent i = getIntent();
        m_foodItem = (Food) i.getParcelableExtra("FOOD");
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
    }


}
