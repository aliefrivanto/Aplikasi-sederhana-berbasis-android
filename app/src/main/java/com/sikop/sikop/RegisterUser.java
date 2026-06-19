package com.sikop.sikop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {
    TextView account;
    EditText inputnama,inputemail, pass1, pass2;
    ProgressBar progressBar;
    Button btnregister;
    FirebaseAuth mAuth;
    boolean passvis;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mAuth = FirebaseAuth.getInstance();

        account = (TextView) findViewById(R.id.account);
        account.setOnClickListener(this);

        inputnama =(EditText) findViewById(R.id.name);


        inputemail =(EditText) findViewById(R.id.emailr);
        pass1 =(EditText) findViewById(R.id.password1);

        pass1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX()>=pass1.getRight()-pass1.getCompoundDrawables()[Right].getBounds().width()){
                        int selec=pass1.getSelectionEnd();
                        if (passvis){
                            //set drawable image
                            pass1.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_visibility_off_24, 0);
                            //for hide
                            pass1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passvis=false;
                        }else {
                            //set drawable image
                            pass1.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_visibility_24, 0);
                            //for hide
                            pass1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passvis=true;
                        }
                        pass1.setSelection(selec);
                        return true;
                    }
                }



                return false;
            }
        });

        pass2 =(EditText) findViewById(R.id.password2);

        pass2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX()>=pass2.getRight()-pass2.getCompoundDrawables()[Right].getBounds().width()){
                        int selec=pass2.getSelectionEnd();
                        if (passvis){
                            //set drawable image
                            pass2.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_visibility_off_24, 0);
                            //for hide
                            pass2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passvis=false;
                        }else {
                            //set drawable image
                            pass2.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_visibility_24, 0);
                            //for hide
                            pass2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passvis=true;
                        }
                        pass2.setSelection(selec);
                        return true;
                    }
                }



                return false;
            }
        });


        btnregister = findViewById(R.id.registerUser);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrasi();
            }
        });


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }
    private void registrasi() {
        String fullName = inputnama.getText().toString();
        String email = inputemail.getText().toString();
        String password1 = pass1.getText().toString();
        String password2 = pass2.getText().toString();

        if (fullName.isEmpty()){
            inputnama.setError("Full Name is Required");
            inputnama.requestFocus();
            return;
        }
        else if (email.isEmpty()){
            inputemail.setError("Email is Required");
            inputemail.requestFocus();
            return;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            inputemail.setError("Please provide valid email");
            inputemail.requestFocus();
            return;
        }
        else if (password1.isEmpty()){
            pass1.setError("Password is Required");
            pass1.requestFocus();
            return;
        }
        else if (password1.length() < 6){
            pass1.setError("Min Password Length should be 6 chacacters!");
            pass1.requestFocus();
            return;
        }
        else if (!password1.equals(password2)) {
            pass2.setError("Password Would Not be matched");
            return;
        }
       /* else{

            databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(email)){
                        Toast.makeText(RegisterUser.this, "email has already registered",Toast.LENGTH_SHORT).show();;
                    }else{
                        databaseReference.child("Users").child(email).child("fullname").setValue(fullName);
                        databaseReference.child("Users").child(email).child("email").setValue(email);
                        databaseReference.child("Users").child(email).child("password").setValue(password2);
                        Toast.makeText(RegisterUser.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        } */

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password2)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(fullName, email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(RegisterUser.this, "Registrasi Berhasil", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                                startActivity(new Intent(RegisterUser.this, MainActivity.class));
                                            } else {
                                                Toast.makeText(RegisterUser.this, "Registrasi Gagal", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }

                                        }
                                    });

                        }
                    }
                });





    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.account:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}