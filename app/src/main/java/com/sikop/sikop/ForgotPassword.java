package com.sikop.sikop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {
    TextView account;
    EditText reset_email;
    Button btn_reset;
    ProgressBar progressBar;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        account = (TextView) findViewById(R.id.account);
        account.setOnClickListener(this);
        reset_email = (EditText) findViewById(R.id.r_email);
        btn_reset = (Button) findViewById(R.id.reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

    }

    private void resetPassword() {
        String email = reset_email.getText().toString().trim();

        if (email.isEmpty()){
            reset_email.setError("Email is required");
            reset_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            reset_email.setError("Please provide valid Email!");
            reset_email.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this, "Check Your Email to reset your password", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(ForgotPassword.this, "Try Again! Something Wrong Happened", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.account:
                startActivity(new Intent(this, MainActivity.class));
        }
    }
}