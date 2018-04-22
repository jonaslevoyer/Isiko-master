package com.isiko.isiko;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity
    {
        private EditText registerMail;
        private EditText registerPwd;
        private FirebaseAuth firebaseAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        registerMail = (EditText) findViewById(R.id.RegisterMail);
        registerPwd = (EditText) findViewById(R.id.RegisterPwd);
        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void btnRegister(View v) {
    final ProgressDialog progressDialog = ProgressDialog.show(RegisterActivity.this, "Please wait...", "Processing...",true);
        (firebaseAuth.createUserWithEmailAndPassword(registerMail.getText().toString(), registerPwd.getText().toString()))
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            progressDialog.dismiss();
            if (task.isSuccessful()) {
                Toast.makeText(RegisterActivity.this, "Inscription reussie", Toast.LENGTH_LONG).show();
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
            else {
                Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        });
    }
}
