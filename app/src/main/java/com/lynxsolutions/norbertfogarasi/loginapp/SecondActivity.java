package com.lynxsolutions.norbertfogarasi.loginapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SecondActivity extends Activity {

    private TextView tvUserInformation;
    private FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        user = FirebaseAuth.getInstance().getCurrentUser();
        initViews();
    }

    private void initViews(){
        //User information textview
        tvUserInformation = (TextView) findViewById(R.id.tv_account_info);

        //Sign out
        findViewById(R.id.btn_log_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(SecondActivity.this, LoginActivity.class));
                finish();
            }
        });

        tvUserInformation.append("\nEmail: " + user.getEmail());
        tvUserInformation.append("\nName: " + user.getDisplayName());
    }

}
