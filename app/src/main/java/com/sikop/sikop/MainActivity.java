package com.sikop.sikop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView register,fpas;
    EditText inputEmail, inputPassword;
    Button btnlogin;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    boolean passvis;
    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox();

        mAuth = FirebaseAuth.getInstance();
        inputEmail = findViewById(R.id.emaill);
        inputPassword = findViewById(R.id.password);

        inputPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX()>=inputPassword.getRight()-inputPassword.getCompoundDrawables()[Right].getBounds().width()){
                        int selec=inputPassword.getSelectionEnd();
                        if (passvis){
                            //set drawable image
                            inputPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_visibility_off_24, 0);
                            //for hide
                            inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passvis=false;
                        }else {
                            //set drawable image
                            inputPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_visibility_24, 0);
                            //for hide
                            inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passvis=true;
                        }
                        inputPassword.setSelection(selec);
                        return true;
                    }
                }



                return false;
            }
        });

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);




        fpas = (TextView) findViewById(R.id.forgotpassword);
        fpas.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnlogin = findViewById(R.id.login);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekLogin();

            }
        });
    }
    private void checkBox() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String check = sharedPreferences.getString("name", "");
        if (check.equals("true")){
            Intent masuk = new Intent(MainActivity.this, File1.class);
            startActivity(masuk);
            finish();
        }
    }

    private void cekLogin() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user.isEmailVerified()){
                                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString("name", "true");
                                editor.apply();
                                progressBar.setVisibility(View.GONE);
                                Intent masuk = new Intent(MainActivity.this, File1.class);
                                startActivity(masuk);
                            }else {
                                user.sendEmailVerification();
                                Toast.makeText(MainActivity.this, "Check Email to Verify", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }

                        }else {
                            Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }




                        }


                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                startActivity(new Intent(this, RegisterUser.class));
                break;
            case R.id.forgotpassword:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
        }
    }
}