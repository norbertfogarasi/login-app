package com.lynxsolutions.norbertfogarasi.loginapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
<<<<<<< HEAD
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

=======
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
>>>>>>> 3c98e8a6087ed16b3d8ced3960d8af0dcf61b92f
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
<<<<<<< HEAD

=======
>>>>>>> 3c98e8a6087ed16b3d8ced3960d8af0dcf61b92f
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
<<<<<<< HEAD
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends Activity {

    private static final String TAG = RegisterActivity.class.getSimpleName();

=======

public class RegisterActivity extends Activity {

>>>>>>> 3c98e8a6087ed16b3d8ced3960d8af0dcf61b92f
    private static final int CAMERA_REQUEST = 1888;
    private static final int SELECT_PHOTO = 1889;

    private Calendar calendar;
    private ImageView imgProfile;
    private EditText etFirstName, etLastName, etEmail, etPassword, etPasswordConfirm;
    private RadioGroup rgGender;
    private RadioButton rbMale, rbFemale;
    private TextView tvDate;

<<<<<<< HEAD
    private FirebaseAuth mAuth;

=======
>>>>>>> 3c98e8a6087ed16b3d8ced3960d8af0dcf61b92f
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
<<<<<<< HEAD
        mAuth = FirebaseAuth.getInstance();
=======
>>>>>>> 3c98e8a6087ed16b3d8ced3960d8af0dcf61b92f
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
<<<<<<< HEAD

=======
>>>>>>> 3c98e8a6087ed16b3d8ced3960d8af0dcf61b92f
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegisterActivity.this, date, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

<<<<<<< HEAD

        //Register
        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                if (validFields()) {
                    createAccount(etEmail.getText().toString(), etPassword.getText().toString());
                }
=======
        //Register
        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateFields();
>>>>>>> 3c98e8a6087ed16b3d8ced3960d8af0dcf61b92f
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

<<<<<<< HEAD
    private boolean validFields() {
        //Checks if the fields are set properly
        //If not, warns the user
        boolean valid = true;

        if (etFirstName.getText().toString().equals("")) {
            valid = false;
            etFirstName.setError(getResources().getString(R.string.err_firstname));
        }
        if (etLastName.getText().toString().equals("")) {
            valid = false;
            etLastName.setError(getResources().getString(R.string.err_lastname));
        }
        if (etEmail.getText().toString().equals("")) {
            valid = false;
            etEmail.setError(getResources().getString(R.string.err_email));
        } else if (!isValidEmail(etEmail.getText().toString())) {
            valid = false;
            etEmail.setError(getResources().getString(R.string.err_email_not_valid));
        }
        if (etPassword.getText().toString().equals("")) {
            valid = false;
            etPassword.setError(getResources().getString(R.string.err_password));
        }
        if (etPasswordConfirm.getText().toString().equals("")) {
            valid = false;
            etPasswordConfirm.setError(getResources().getString(R.string.err_password_confirm));
        } else if (!etPasswordConfirm.getText().toString().equals(
                etPassword.getText().toString())) {
            //If the passwords don't match
            valid = false;
            etPasswordConfirm.setError(getResources().getString(R.string.err_passwords_dont_match));
        }
        if (tvDate.getText().toString().equals(getResources().getString(R.string.tv_birthday))) {
            valid = false;
=======
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
>>>>>>> 3c98e8a6087ed16b3d8ced3960d8af0dcf61b92f
            tvDate.setError(getResources().getString(R.string.err_birthday));
        } else {
            tvDate.setError(null);
        }
<<<<<<< HEAD
        if (rgGender.getCheckedRadioButtonId() <= 0) {
            valid = false;
            rbFemale.setError(getResources().getString(R.string.err_gender));
        } else {
            rbFemale.setError(null);
        }
        return valid;
    }


=======
        if(rgGender.getCheckedRadioButtonId()<=0){//Grp is your radio group object
            rbFemale.setError(getResources().getString(R.string.err_gender));
        }
        else {
            rbFemale.setError(null);
        }
    }

>>>>>>> 3c98e8a6087ed16b3d8ced3960d8af0dcf61b92f
    private boolean isValidEmail(CharSequence target) {
        //Checks if an e-mail address is valid or not
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
<<<<<<< HEAD

    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Log.d(TAG, "createAccount: " + task.getException());
                            Toast.makeText(RegisterActivity.this, "Failed to sign up", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
=======
>>>>>>> 3c98e8a6087ed16b3d8ced3960d8af0dcf61b92f
}