package com.project.glm.foodproject;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


/**
 * Created by glm on 03/08/2017.
 */

public class Common {

    static String doubleToPrice(double _dbl) {
        Locale locale = new Locale("fr", "FR");
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern("##,###.00 €");
        return decimalFormat.format(_dbl);
    }

    static class ProcessRequest extends AsyncTask<Object, Void, String> {

        @Override
        protected String doInBackground(Object[] _params) {
            String url = (String) _params[0];
            JSONObject jsonParam = (JSONObject) _params[1];
            return Common.postRequest(url, jsonParam);
        }

        @Override
        protected void onPostExecute(String message) {
            //TODO
        }
    }

    public static String postRequest(String _path, JSONObject _jsonData) {
        String response = "";
        try {
            URL url = new URL(_path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type","application/json");
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(_jsonData.toString());
            writer.flush();
            writer.close();
            os.close();

            int responseCode=conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static boolean isLandscapeOrientation(Activity _activity) {
        return _activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public static boolean isLargeScreenLayout(Activity _activity) {
        return ((_activity.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE)
                || ((_activity.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
    }
}
