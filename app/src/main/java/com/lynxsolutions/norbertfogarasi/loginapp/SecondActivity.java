package com.lynxsolutions.norbertfogarasi.loginapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();
    }

    private void initViews(){
        //Back button
        findViewById(R.id.btn_logged_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this, LoginActivity.class));
                finish();
            }
        });

    }

}
