package com.lynxsolutions.norbertfogarasi.loginapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RegisterActivity extends Activity {

    private static final int CAMERA_REQUEST = 1888;
    private static final int SELECT_PHOTO = 1889;

    private Calendar calendar;
    private ImageView imgProfile;
    private EditText etFirstName, etLastName, etEmail, etPassword, etPasswordConfirm;
    private RadioGroup rgGender;
    private RadioButton rbMale, rbFemale;
    private TextView tvDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
    }

    private void initViews() {
        imgProfile = (ImageView) findViewById(R.id.img_profile);
        etFirstName = (EditText) findViewById(R.id.et_firstname);
        etLastName = (EditText) findViewById(R.id.et_lastname);
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        etPasswordConfirm = (EditText) findViewById(R.id.et_password_confirm);
        tvDate = (TextView) findViewById(R.id.tv_birthday);
        rgGender = (RadioGroup) findViewById(R.id.rg_gender);
        rbMale = (RadioButton) findViewById(R.id.rb_male);
        rbFemale = (RadioButton) findViewById(R.id.rb_female);

        //Capture an image
        findViewById(R.id.btn_capture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAMERA_REQUEST);
            }
        });

        //Choose an image from gallery
        findViewById(R.id.btn_choose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pictureIntent = new Intent();
                pictureIntent.setType("image/*");
                startActivityForResult(pictureIntent, SELECT_PHOTO);
            }
        });

        //Date picker
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

        //Register
        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateFields();
            }
        });
    }

    private void updateTextView() {
        //Updates the textview with the chosen date
        String format = "yyyy.MM.dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        tvDate.setText(sdf.format(calendar.getTime()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (resultCode == Activity.RESULT_OK) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    imgProfile.setImageBitmap(photo);
                }
                break;
            case SELECT_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = data.getData();
                    try {
                        InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                        Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                        imgProfile.setImageBitmap(yourSelectedImage);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }

    private void validateFields() {
        //Checks if the fields are set properly
        //If not, warns the user
        if (etFirstName.getText().toString().equals("")) {
            etFirstName.setError(getResources().getString(R.string.err_firstname));
        }
        if (etLastName.getText().toString().equals("")) {
            etLastName.setError(getResources().getString(R.string.err_lastname));
        }
        if (etEmail.getText().toString().equals("")) {
            etEmail.setError(getResources().getString(R.string.err_email));
        } else if (!isValidEmail(etEmail.getText().toString())) {
            etEmail.setError(getResources().getString(R.string.err_email_not_valid));
        }
        if(etPassword.getText().toString().equals("")) {
            etPassword.setError(getResources().getString(R.string.err_password));
        }
        if(etPasswordConfirm.getText().toString().equals("")) {
            etPasswordConfirm.setError(getResources().getString(R.string.err_password_confirm));
        } else if(!etPasswordConfirm.getText().toString().equals(
                etPassword.getText().toString())) {
            //If the passwords don't match
            etPasswordConfirm.setError(getResources().getString(R.string.err_passwords_dont_match));
        }
        if(tvDate.getText().toString().equals(getResources().getString(R.string.tv_birthday))) {
            tvDate.setError(getResources().getString(R.string.err_birthday));
        } else {
            tvDate.setError(null);
        }
        if(rgGender.getCheckedRadioButtonId()<=0){//Grp is your radio group object
            rbFemale.setError(getResources().getString(R.string.err_gender));
        }
        else {
            rbFemale.setError(null);
        }
    }

    private boolean isValidEmail(CharSequence target) {
        //Checks if an e-mail address is valid or not
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}