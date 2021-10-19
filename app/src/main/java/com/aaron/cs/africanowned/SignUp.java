package com.aaron.cs.africanowned;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    EditText uName, uEmail, uPassword;
    TextView uLoginButton;
    Button uSignUpButton;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        uName = findViewById(R.id.Name);
        uEmail = findViewById(R.id.email);
        uPassword = findViewById(R.id.password);
        uLoginButton = findViewById(R.id.alreadyRegistered);
        uSignUpButton = findViewById(R.id.signUp);

        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        uSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = uName.getText().toString().trim();
                String email = uEmail.getText().toString().trim();
                String password = uPassword.getText().toString().trim();

                if(TextUtils.isEmpty(fullName)) {
                    uName.setError("Name is required.");
                    return;
                }
                if(TextUtils.isEmpty(email)) {
                    uEmail.setError("Email is required.");
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    uPassword.setError("Password is required.");
                    return;
                }
                if(password.length() < 7) {
                    uPassword.setError("Password must be at least 7 characters long.");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(SignUp.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        uLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LogIn.class));
            }
        });

    }
}