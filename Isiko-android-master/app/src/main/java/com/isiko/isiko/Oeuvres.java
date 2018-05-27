package com.isiko.isiko;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Oeuvres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oeuvres);
    }
    public void btnExhibitions(View v){
        String url = "http://api.isiko.io/api/getExhibitionses/";
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final StringRequest ExhibitionsRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("VOLLEY", response);
                    JSONArray jsonArray=getJSonData(response);
                    ArrayList<JSONObject> listItems=getArrayListFromJSONArray(jsonArray);
                    ListView listV=(ListView)findViewById(R.id.listv);
                    ListAdapter adapter=new ListAdapter(getApplicationContext(),R.layout.list_layout,R.id.exponame,listItems);
                    listV.setAdapter(adapter);}

                //private ArrayList<String> jsonStringToArray(String jsonString) throws JSONException {
                //    ArrayList<String> stringArray = new ArrayList<>();
                //    JSONArray jsonArray = new JSONArray(jsonString);
                //    for (int i = 0; i < jsonArray.length(); i++) {
                //        stringArray.add(jsonArray.getString(i));
                //    }
                //    return stringArray;
                //}

                private JSONArray getJSonData(String json_text){
                    JSONArray jsonArray = null;
                    //json_text = "[" + json_text + "]";
                    try {
                        jsonArray = new JSONArray(json_text);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return jsonArray;}

                private ArrayList<JSONObject> getArrayListFromJSONArray(JSONArray jsonArray){
                    ArrayList<JSONObject> aList=new ArrayList<JSONObject>();
                    try {
                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                aList.add(jsonArray.getJSONObject(i));
                            }
                        }
                    }catch (JSONException je){je.printStackTrace();}
                    return  aList;}

            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("VOLLEY", error.toString());
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/json");
                    params.put("Authorization", "Basic " + Base64.encodeToString("maxime:Cyr10277".getBytes(), Base64.NO_WRAP));
                    return params;
                }
            };
            requestQueue.add(ExhibitionsRequest);
    }
}


