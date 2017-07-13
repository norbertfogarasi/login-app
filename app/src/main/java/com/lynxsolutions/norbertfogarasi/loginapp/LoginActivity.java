package com.lynxsolutions.norbertfogarasi.loginapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class LoginActivity extends Activity {

    private EditText etEmail;
    private EditText etPassword;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Firebase
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    //User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in: " + user.getUid());
                } else {
                    //User is signed out
                    Log.d(TAG, "onAuthStateChanged: signed_out");
                }
            }
        };

        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void initViews() {
        //EditText for email
        etEmail = (EditText) findViewById(R.id.et_login_email);

        //EditText for password
        etPassword = (EditText) findViewById(R.id.et_login_password);

        //Login button
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validFields()) {
                    if(validFields()) {
                        signIn(etEmail.getText().toString(), etPassword.getText().toString());
                    }
                }
            }
        });

        //Sign up button
        findViewById(R.id.btn_sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });
    }

    //Firebase
    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInUserWithEmail:onComplete:" + task.isSuccessful());
                        if(!task.isSuccessful()) {
                            Log.w(TAG, "signedInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, "Failed to log in", Toast.LENGTH_LONG).show();
                        } else {
                            startActivity(new Intent(LoginActivity.this, SecondActivity.class));
                            finish();
                        }
                    }
                });
    }

    private boolean validFields() {
        boolean valid = true;
        if(etEmail.getText().toString().equals("")) {
            valid = false;
            etEmail.setError(getString(R.string.err_email));
        }
        if(etPassword.getText().toString().equals("")) {
            valid = false;
            etPassword.setError(getString(R.string.err_login_password));
        }
        return valid;
    }
}
