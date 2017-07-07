package com.lynxsolutions.norbertfogarasi.loginapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity {

    private Calendar calendar;
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

        //Date picker
        calendar = Calendar.getInstance();

        calendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, dayOfMonth);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateTextView();
            }
        };
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegisterActivity.this, date, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateTextView() {
        //Updates the textview with the chosen date
        String format = "yyyy.MM.dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        tvDate.setText(sdf.format(calendar.getTime()));
    }
}
