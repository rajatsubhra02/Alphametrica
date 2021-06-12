package com.rajat.alphametrica.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rajat.alphametrica.R;

import androidx.appcompat.app.AppCompatActivity;

public class LandActivity extends AppCompatActivity {

    TextView tvSignUp, tvLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land);

        tvSignUp = findViewById(R.id.tvSignUp);
        tvLogin = findViewById(R.id.tvLogin);

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openSignUpActivity();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });
    }

    private void openSignUpActivity() {
        Intent mIntent = new Intent(this, SignUpActivity.class);
        startActivity(mIntent);
    }

    private void openLoginActivity() {
        Intent mIntent = new Intent(this, LoginActivity.class);
        startActivity(mIntent);
    }
}
