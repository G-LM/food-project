package com.project.glm.foodproject;

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


/**
 * Created by glm on 03/08/2017.
 */

public class Common {

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
}
