package com.aaron.cs.africanowned;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aaron.cs.africanowned.R;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        setContentView( R.layout.activity_sign_up);

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
                String phone = uPhone.getText().toString().trim();

                if(TextUtils.isEmpty(name)) {
                    uName.setError("Name is required.");
                    return;
                }
                if(TextUtils.isEmpty(email)) {
                    uEmail.setError("Email is required.");
                    isValidEmail(email);
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    uPassword.setError("Password is required.");
                    return;
                }
                if(password.length() < 7 || !isValidPassword(password)) {
                    uPassword.setError("Password must contain 1 Uppercase, 1 Number, 1 Symbol and be at least 7 characters long.");
                    return;
                }
                if(TextUtils.isEmpty(phone)) {
                    uPhone.setError("Phone is required.");
                    isValidPhoneNumber(phone);
                    return;
                }
                if(phone.length() < 10 || !isValidPhoneNumber(phone)) {
                    uPhone.setError("Phone must be valid and at least 10 numbers long.");
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
        });

        uLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LogIn.class));
            }
        });

    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

    private boolean isValidPhoneNumber(CharSequence phoneNumber) {
        if (phoneNumber == null)
            return false;

        return Patterns.PHONE.matcher(phoneNumber).matches();

    }
}



