package com.rajat.alphametrica.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rajat.alphametrica.R;
import com.rajat.alphametrica.utils.Utility;

public class LoginActivity extends AppCompatActivity {

    TextView tvSignUp, tvLogin, tvForgotPassword;
    EditText edtEmail, edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inIt();

    }

    private void inIt() {
        tvSignUp = findViewById(R.id.tvSignUp);
        tvLogin = findViewById(R.id.tvLogin);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignUpActivity();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkField()){
                    if(edtEmail.getText().toString().trim().equalsIgnoreCase("test@luxpmsoft.com") && edtPassword.getText().toString().trim().equalsIgnoreCase("test1234!"))
                    openMainActivity();
                }
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), getString(R.string.forgot_password), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openMainActivity() {
        Intent mIntent = new Intent(this, MainActivity.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mIntent);
        this.finish();
    }

    private void openSignUpActivity() {
        Intent mIntent = new Intent(this, SignUpActivity.class);
        startActivity(mIntent);
        this.finish();
    }

    private boolean checkField() {
        if(!Utility.isValidEmail(edtEmail.getText().toString().trim())){
            Toast.makeText(getApplicationContext(), "InValid Email Address.", Toast.LENGTH_SHORT).show();
            return false;
        } else  if(edtPassword.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter your Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
