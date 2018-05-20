package com.isiko.isiko;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity
    {
        private EditText registerName;
        private EditText registerMail;
        private EditText registerPwd;
        private JSONArray name;
        private JSONArray mail;
        private JSONArray pwd;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        registerName = (EditText) findViewById(R.id.RegisterName);
        registerMail = (EditText) findViewById(R.id.RegisterMail);
        registerPwd = (EditText) findViewById(R.id.RegisterPwd);
        name = new JSONArray();
        mail = new JSONArray();
        pwd = new JSONArray();

    }
    public void btnRegister(View v) {
        String url = "http://api.isiko.io/api/subcribeUsers/";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            name.put(registerName.getText().toString());
            mail.put(registerMail.getText().toString());
            pwd.put(registerPwd.getText().toString());
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("username", name);
            jsonBody.put("usermail", mail);
            jsonBody.put("password", pwd);
            Log.d("TEST", jsonBody.toString());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
            new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("VOLLEY", response.toString());
                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("VOLLEY", error.toString());
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/json");
                    params.put("Authorization", "Basic " + Base64.encodeToString("maxime:Cyr10277".getBytes(), Base64.NO_WRAP));
                    return params;
                }
            };
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
