package com.isiko.isiko;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText loginMail;
    private EditText loginPwd;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        loginMail = (EditText) findViewById(R.id.LoginMail);
        loginPwd = (EditText) findViewById(R.id.LoginPwd);
        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();
        String url = "http://api.isiko.io/api/loginUsers/";
        try {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("Pseudo", "maxime");
        jsonBody.put("Password", "Cyr10277");
        final String requestBody = jsonBody.toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("VOLLEY", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void btnLogin(View v) {
        final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Please wait...", "Processing...",true);
        (firebaseAuth.signInWithEmailAndPassword(loginMail.getText().toString(), loginPwd.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Connexion reussie", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(LoginActivity.this, NavActivity.class);
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}