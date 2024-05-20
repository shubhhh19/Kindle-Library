package com.shubh.kindleLibrary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginUser extends AppCompatActivity implements View.OnClickListener{

    private TextView register;
    private EditText email, pass;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private Button Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        register = findViewById(R.id.textView4);
        register.setOnClickListener(this);

        Btn = findViewById(R.id.button);
        Btn.setOnClickListener(this);

        email = findViewById(R.id.editTextTextEmailAddress);
        pass = findViewById(R.id.editTextTextPassword);
        progressBar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textView4:
                startActivity(new Intent(this,RegisterUser.class));
                break;

            case R.id.button:
                loginUser();
                break;

        }
    }
    private void loginUser(){

        // Take the value of two edit texts in Strings
        String e_mail = email.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if(e_mail.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }
        //checks if email is valid
        if(!Patterns.EMAIL_ADDRESS.matcher(e_mail).matches()){
            email.setError("Please provide a valid email!");
            email.requestFocus();
            return;
        }
        //checks if password  is empty
        if(password.isEmpty()){
            pass.setError("Password is required!");
            pass.requestFocus();
            return;
        }

        if(password.length() < 6){
            pass.setError("Min password should be 6 characters!");
            pass.requestFocus();
            return;
        }
        // show the visibility of progress bar to show loading
        progressBar.setVisibility(View.VISIBLE);

        // sign in existing user
        mAuth.signInWithEmailAndPassword(e_mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                }else{
                    Toast.makeText(LoginUser.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                    // hide the progress bar
                }
                progressBar.setVisibility(View.GONE);

            }
        });
    }
}