package com.shubh.kindleLibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar3);
        if (auth.getCurrentUser() != null){
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(HomeActivity.this,MainActivity.class));
            Toast.makeText(this,"Please wait" ,Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void login(View view) {
        startActivity(new Intent(HomeActivity.this,LoginUser.class));
    }

    public void registration(View view) {
        startActivity(new Intent(HomeActivity.this,RegisterUser.class));
    }
}