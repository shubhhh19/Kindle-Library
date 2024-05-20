package com.shubh.kindleLibrary;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class RegisterUser extends AppCompatActivity {

    private EditText fullname, email, pass, conf_pass;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private Button Btn;

    private ImageView iv_mic, iv_mic1;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        //initialization of objects
        mAuth = FirebaseAuth.getInstance();

        fullname = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextTextEmailAddress2);
        pass = findViewById(R.id.editTextTextPassword2);
        conf_pass = findViewById(R.id.editTextTextPassword3);
        progressBar = findViewById(R.id.progressBar2);
        Btn = findViewById(R.id.button2);

        // Set on Click Listener on Registration button
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                registerUser();
            }
        });

        db = FirebaseFirestore.getInstance();

        iv_mic = findViewById(R.id.rec_voice);
        iv_mic1 = findViewById(R.id.rec_voice1);

    }

    public void registerUser(){

        // show the visibility of progress bar to show loading
        progressBar.setVisibility(View.VISIBLE);

        // Take the value of edit texts in Strings
        String fullName = fullname.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String confirm_password = conf_pass.getText().toString().trim();
        String e_mail = email.getText().toString().trim();

        //checks if full name is empty
        if(fullName.isEmpty()){
            fullname.setError("Full name is required!");
            fullname.requestFocus();
            return;
        }
        //checks if email is empty
        if(e_mail.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }
        //checks if email is valid
        if(!Patterns.EMAIL_ADDRESS.matcher(e_mail).matches()){
            email.setError("Please provide valid email!");
            email.requestFocus();
            return;
        }
        //checks if password  is empty
        if(password.isEmpty()){
            pass.setError("Password is required!");
            pass.requestFocus();
            return;
        }
        //checks if retyped password  is empty
        if(confirm_password.isEmpty()){
            conf_pass.setError("Password is required!");
            conf_pass.requestFocus();
            return;
        }
        if(password.length() < 6){
            pass.setError("Min password should be 6 characters!");
            pass.requestFocus();
            return;
        }
        //checks if password and retyped password are the same
        if(!password.equals(confirm_password)){
            Toast.makeText(this, "Passwords must be the same!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        // create new user or register new user
        mAuth
                .createUserWithEmailAndPassword(e_mail, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            CollectionReference collectionReference = db.collection("users");

                            Map<String, Object> User = new HashMap<>();
                            User.put("fullname",fullName);
                            User.put("email", e_mail);
                            User.put("password", password);

                            collectionReference.add(User).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    // after the data addition is successful
                                    // we are displaying a success toast message.
                                    Toast.makeText(RegisterUser.this, "Successful registration!", Toast.LENGTH_SHORT).show();
                                    // hide the progress bar
                                    progressBar.setVisibility(View.GONE);

                                    // if the user created intent to login activity
                                    Intent intent = new Intent(RegisterUser.this, LoginUser.class);
                                    startActivity(intent);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Registration failed
                                    // this method is called when the data addition process is failed.
                                    // displaying a toast message when data addition is failed.
                                    Toast.makeText(RegisterUser.this, "Registration failed!!" + " Please try again later \n" + e, Toast.LENGTH_SHORT).show();

                                    // hide the progress bar
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                        }
                        else {

                            // Registration failed
                            Toast.makeText(getApplicationContext(), "Registration failed!!" + " Please try again later", Toast.LENGTH_LONG).show();

                            // hide the progress bar
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    public void onClick(View view){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        }
        catch (Exception e) {
            Toast.makeText(RegisterUser.this, " " + e.getMessage(), Toast.LENGTH_SHORT).show();
        };

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                fullname.setText(Objects.requireNonNull(result).get(0));
            }
        }
    }

}