package com.isiko.isiko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
    }

    public void registerButtonClick(View v) {
        Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }

    public void loginButtonClick(View v) {
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);

    }
}
