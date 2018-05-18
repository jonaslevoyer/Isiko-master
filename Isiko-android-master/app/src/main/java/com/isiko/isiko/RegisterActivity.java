package com.isiko.isiko;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity
    {
        private EditText registerName;
        private EditText registerMail;
        private EditText registerPwd;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        registerName = (EditText) findViewById(R.id.RegisterName);
        registerMail = (EditText) findViewById(R.id.RegisterMail);
        registerPwd = (EditText) findViewById(R.id.RegisterPwd);
    }
    public void btnRegister(View v) {
        String url = "http://api.isiko.io/api/subcribeUsers/";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);

            JSONObject postparams = new JSONObject();
            postparams.put("username", registerName.toString());
            postparams.put("usermail", registerMail.toString());
            postparams.put("password", registerPwd.toString());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postparams, new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("VOLLEY", response.toString());
                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("VOLLEY", error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
