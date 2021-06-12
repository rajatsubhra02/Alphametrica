package com.rajat.alphametrica.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rajat.alphametrica.R;
import com.rajat.alphametrica.utils.Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {


    TextView tvSignUp, tvLogin, tvDOBInput;
    EditText edtName, edtMobile,  edtEmail, edtPassword, edtConfirmPassword;
    ImageView imgCharSize, imgOneUpperChar, imgOneNumber, imgOneSpecial;
    CheckBox cbTerm;

    Pattern patternAllCond= Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*?(){}])(?=\\S+$).{8,}$");
    Pattern patternUpper = Pattern.compile("^(?=.*[A-Z]).{1,}$");
    Pattern patternNumber = Pattern.compile("^(?=.*[0-9]).{1,}$");
    Pattern patternSpecial = Pattern.compile("^(?=.*[@#$%^&+=!*?(){}]).{1,}$");

    String dateDoB = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        inIt();
    }

    private void inIt() {
        tvSignUp = findViewById(R.id.tvSignUp);
        tvLogin = findViewById(R.id.tvLogin);

        edtName = findViewById(R.id.edtName);
        edtMobile = findViewById(R.id.edtMobile);
        tvDOBInput = findViewById(R.id.tvDOBInput);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        imgCharSize = findViewById(R.id.imgCharSize);
        imgOneUpperChar = findViewById(R.id.imgOneUpperChar);
        imgOneNumber = findViewById(R.id.imgOneNumber);
        imgOneSpecial = findViewById(R.id.imgOneSpecial);
        cbTerm = findViewById(R.id.cbTerm);

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fieldValidation()){
                    openMainActivity();
                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });
        tvDOBInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialogDateDob();

            }
        });

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String passwordStr = charSequence.toString();
                if (patternNumber.matcher(passwordStr).matches())
                    imgOneNumber.setImageResource(R.drawable.ic_checked);
                else
                    imgOneNumber.setImageResource(R.drawable.ic_unchecked);

                if ((patternUpper.matcher(charSequence)).matches())
                    imgOneUpperChar.setImageResource(R.drawable.ic_checked);
                else
                    imgOneUpperChar.setImageResource(R.drawable.ic_unchecked);

                if(patternSpecial.matcher(passwordStr).matches())
                    imgOneSpecial.setImageResource(R.drawable.ic_checked);
                else
                    imgOneSpecial.setImageResource(R.drawable.ic_unchecked);

                if(passwordStr.toString().length() >7)
                    imgCharSize.setImageResource(R.drawable.ic_checked);
                else
                    imgCharSize.setImageResource(R.drawable.ic_unchecked);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void openLoginActivity() {
        Intent mIntent = new Intent(this, LoginActivity.class);
        startActivity(mIntent);
        this.finish();
    }


    private void openMainActivity() {
        Intent mIntent = new Intent(this, MainActivity.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mIntent);
        this.finish();
    }

    private boolean fieldValidation(){
        if(edtName.getText().toString().trim().isEmpty()){
            Toast.makeText(SignUpActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if(edtMobile.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter your Mobile number", Toast.LENGTH_SHORT).show();
            return false;
        }   else if(dateDoB.isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter your Date of Birth", Toast.LENGTH_SHORT).show();
            return false;
        }   else if(!Utility.isValidEmail(edtEmail.getText().toString().trim())){
            Toast.makeText(getApplicationContext(), "InValid Email Address.", Toast.LENGTH_SHORT).show();
            return false;
        } else  if(edtPassword.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter your Password", Toast.LENGTH_SHORT).show();
            return false;
        }  else  if(patternAllCond.matcher(edtPassword.getText().toString()).matches()){
            Toast.makeText(getApplicationContext(), "Enter your valid Password", Toast.LENGTH_SHORT).show();
            return false;
        }  else  if(edtConfirmPassword.getText().toString().trim().isEmpty() && edtConfirmPassword.getText().toString().trim().equalsIgnoreCase(edtPassword.getText().toString().trim())){
            Toast.makeText(getApplicationContext(), "Please your Confirm Password", Toast.LENGTH_SHORT).show();
            return false;
        } else if(!cbTerm.isChecked()){
            Toast.makeText(getApplicationContext(), "Please check the Term And Condition", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    public void DatePickerDialogDateDob() {
        final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Calendar mCurrentDate = Calendar.getInstance();
        int year = mCurrentDate.get(Calendar.YEAR);
        int month = mCurrentDate.get(Calendar.MONTH);
        int day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog mDatePicker;
        mDatePicker = new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                java.util.Calendar cal= java.util.Calendar.getInstance();
                cal.set(java.util.Calendar.DATE, day);
                cal.set(java.util.Calendar.MONTH, month);
                cal.set(java.util.Calendar.YEAR, year);
                dateDoB=sdf.format(cal.getTime());
                tvDOBInput.setText(dateDoB);
            }
        }, year, month, day);
        mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
        mDatePicker.setTitle(SignUpActivity.this.getResources().getString(R.string.dob));
        mDatePicker.show();
    }
}
