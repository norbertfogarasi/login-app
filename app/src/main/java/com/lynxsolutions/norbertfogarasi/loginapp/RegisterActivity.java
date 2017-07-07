package com.lynxsolutions.norbertfogarasi.loginapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import java.util.Calendar;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity {

    private EditText etFirstName, etLastName, etEmail, etPassword, etPasswordConfirm;
    private TextView tvDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
    }

    private void initViews() {
        etFirstName = (EditText) findViewById(R.id.et_firstname);
        etLastName = (EditText) findViewById(R.id.et_lastname);
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        etPasswordConfirm = (EditText) findViewById(R.id.et_password_confirm);
        tvDate = (TextView) findViewById(R.id.tv_birthday);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                    }
                };
            }
        });
    }
}
