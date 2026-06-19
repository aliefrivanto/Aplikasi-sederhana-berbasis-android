package com.sikop.sikop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Logout extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth mAuth;
    TextView lemail, account;
    ImageView back;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        mAuth = FirebaseAuth.getInstance();



        back = (ImageView) findViewById(R.id.tkembali);
        back.setOnClickListener(this);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        account = (TextView) findViewById(R.id.fullName);
        lemail = (TextView) findViewById(R.id.email);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile !=null){
                    String fullName = userProfile.fullName;
                    String email = userProfile.email;

                    account.setText(fullName);
                    lemail.setText(email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Logout.this,"Something wrong happened",Toast.LENGTH_LONG).show();
            }
        });

    }


    public void logout(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name", "");
        editor.apply();

        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        Intent keluar = new Intent(this, MainActivity.class);
        startActivity(keluar);
        finish();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tkembali:
                startActivity(new Intent(this, File1.class));
                break;
        }
    }
}