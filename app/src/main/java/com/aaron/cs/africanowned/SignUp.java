package com.aaron.cs.africanowned;


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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    EditText uName, uEmail, uPassword, uPhone;
    TextView uLoginButton;
    Button uSignUpButton;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        uName = findViewById(R.id.Name);
        uEmail = findViewById(R.id.email);
        uPassword = findViewById(R.id.password);
        uPhone = findViewById(R.id.phone);
        uLoginButton = findViewById(R.id.alreadyRegistered);
        uSignUpButton = findViewById(R.id.signUp);

        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }


        uSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = uName.getText().toString().trim();
                String email = uEmail.getText().toString().trim();
                String password = uPassword.getText().toString().trim();
                String phone = uPhone.getText().toString();

                if(TextUtils.isEmpty(name)) {
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
                if(TextUtils.isEmpty(phone)) {
                    uPhone.setError("Phone is required.");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("name", name);
                            user.put("email", email);
                            user.put("phone", phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(SignUp.this, "User info added", Toast.LENGTH_SHORT).show();
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(SignUp.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

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

            fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener( task -> {
                if(task.isSuccessful()) {
                    Toast.makeText(SignUp.this, "User Created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(SignUp.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            } );
        } );

        uLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LogIn.class));
            }
        });

    }
}