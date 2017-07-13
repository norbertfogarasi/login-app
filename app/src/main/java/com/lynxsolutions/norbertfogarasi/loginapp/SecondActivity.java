package com.lynxsolutions.norbertfogarasi.loginapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

<<<<<<< HEAD
import com.google.firebase.auth.FirebaseAuth;

=======
>>>>>>> 3c98e8a6087ed16b3d8ced3960d8af0dcf61b92f
public class SecondActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();
    }

    private void initViews(){
<<<<<<< HEAD
        //Sign out
        findViewById(R.id.btn_log_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
=======
        //Back button
        findViewById(R.id.btn_logged_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
>>>>>>> 3c98e8a6087ed16b3d8ced3960d8af0dcf61b92f
                startActivity(new Intent(SecondActivity.this, LoginActivity.class));
                finish();
            }
        });
<<<<<<< HEAD
=======

>>>>>>> 3c98e8a6087ed16b3d8ced3960d8af0dcf61b92f
    }

}
