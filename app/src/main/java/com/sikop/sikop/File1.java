package com.sikop.sikop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class File1 extends AppCompatActivity implements View.OnClickListener {
    TextView welcome;
    ImageView tomaps,logout,tsetting;
    ImageView uimg;
    TextView a,b,c,e,f;
    ImageView d,g,h;
    ImageView peraturan;
    TextView ppermen;
    ImageView pik,pnbp,skm;
    TextView ok,sl;
    private long backPress;
    private Toast backToast;

    ImageView back,back1;


    ImageView link1,link2,link3,link4,link5;


    LinearLayout tampilan,tampilan1;
    Button go,go1;
    EditText inputpw,inputpw1;
    boolean passvis,passvis1;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;


    private ImageView imageview1,imageview2,imageview3,imageview4;
    private TextView image1,image2,image3,image4;
    FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file1);
        imageview1 = (ImageView)findViewById(R.id.imageview1);
        imageview2 = (ImageView)findViewById(R.id.imageview2);
        imageview3 = (ImageView)findViewById(R.id.imageview3);
        imageview4 = (ImageView)findViewById(R.id.imageview4);
        image1 = (TextView)findViewById(R.id.img1);
        image2 = (TextView)findViewById(R.id.img2);
        image3 = (TextView)findViewById(R.id.img3);
        image4 = (TextView)findViewById(R.id.img4);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        welcome = (TextView) findViewById(R.id.welcomee);
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile !=null){
                    String fullName = userProfile.fullName;

                    welcome.setText("Welcome, "+fullName);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(File1.this,"Something wrong happened",Toast.LENGTH_LONG).show();
            }
        });
        DocumentReference user = db.collection("BannerImage").document("image");
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot doc = task.getResult();

                    StringBuilder img1 = new StringBuilder("");
                    img1.append(doc.get("image1"));
                    image1.setText(img1.toString());
                    String image1url = image1.getText().toString();
                    Picasso.get().load(image1url).into(imageview1);

                    StringBuilder img2 = new StringBuilder("");
                    img2.append(doc.get("image2"));
                    image2.setText(img2.toString());
                    String image2url = image2.getText().toString();
                    Picasso.get().load(image2url).into(imageview2);

                    StringBuilder img3 = new StringBuilder("");
                    img3.append(doc.get("image3"));
                    image3.setText(img3.toString());
                    String image3url = image3.getText().toString();
                    Picasso.get().load(image3url).into(imageview3);

                    StringBuilder img4 = new StringBuilder("");
                    img4.append(doc.get("image4"));
                    image4.setText(img4.toString());
                    String image4url = image4.getText().toString();
                    Picasso.get().load(image4url).into(imageview4);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(File1.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

        link1 = (ImageView) findViewById(R.id.ig);
        link1.setOnClickListener(this);

        link2 = (ImageView) findViewById(R.id.yt);
        link2.setOnClickListener(this);

        link3 = (ImageView) findViewById(R.id.twit);
        link3.setOnClickListener(this);

        link4 = (ImageView) findViewById(R.id.fb);
        link4.setOnClickListener(this);

        link5 = (ImageView) findViewById(R.id.wa);
        link5.setOnClickListener(this);

        a = (TextView) findViewById(R.id.ppk);
        a.setOnClickListener(this);

        b = (TextView) findViewById(R.id.haccp);
        b.setOnClickListener(this);

        c = (TextView) findViewById(R.id.ckib);
        c.setOnClickListener(this);

        d = (ImageView) findViewById(R.id.bkipm);
        d.setOnClickListener(this);

        e = (TextView) findViewById(R.id.satudata);
        e.setOnClickListener(this);

        f = (TextView) findViewById(R.id.sampel);
        f.setOnClickListener(this);

        g = (ImageView) findViewById(R.id.satgashpik);
        g.setOnClickListener(this);

        skm = (ImageView) findViewById(R.id.skm);
        skm.setOnClickListener(this);

        ok = (TextView) findViewById(R.id.organo);
        ok.setOnClickListener(this);

        back = (ImageView) findViewById(R.id.kkembali);
        back.setOnClickListener(this);

        logout = (ImageView) findViewById(R.id.logout);
        logout.setOnClickListener(this);

        tomaps = (ImageView) findViewById(R.id.alamat);
        tomaps.setOnClickListener(this);

        pnbp = (ImageView) findViewById(R.id.pnbp);
        pnbp.setOnClickListener(this);

        tsetting = (ImageView) findViewById(R.id.seting);
        tsetting.setOnClickListener(this);

        uimg = (ImageView) findViewById(R.id.uploadimg);
        uimg.setOnClickListener(this);

        peraturan = (ImageView) findViewById(R.id.peraturank);
        peraturan.setOnClickListener(this);

        tampilan = (LinearLayout) findViewById(R.id.tampil);

        go = (Button) findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekLogin();
            }
        });

        inputpw = findViewById(R.id.pw);

        inputpw.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX()>=inputpw.getRight()-inputpw.getCompoundDrawables()[Right].getBounds().width()){
                        int selec=inputpw.getSelectionEnd();
                        if (passvis){
                            //set drawable image
                            inputpw.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_visibility_off_24, 0);
                            //for hide
                            inputpw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passvis=false;
                        }else {
                            //set drawable image
                            inputpw.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_visibility_24, 0);
                            //for hide
                            inputpw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passvis=true;
                        }
                        inputpw.setSelection(selec);
                        return true;
                    }
                }



                return false;
            }
        });

    }

    private void cekLogin() {
        String pass = "bkipm240";
        if (inputpw.getText().toString().equalsIgnoreCase(pass)){
            tampilan.setVisibility(View.GONE);
            startActivity(new Intent(this, FormOrganoleptik.class));
        }else {
            Toast.makeText(File1.this, "Password Salah", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (backPress + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else {
            backToast = Toast.makeText(getBaseContext(), "Double Click To Logout", Toast.LENGTH_LONG);
            backToast.show();
        }
        backPress = System.currentTimeMillis();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ig:
                startActivity(new Intent(this, IG.class));
                break;
            case R.id.yt:
                startActivity(new Intent(this, YT.class));
                break;
            case R.id.twit:
                startActivity(new Intent(this, Twitter.class));
                break;
            case R.id.fb:
                startActivity(new Intent(this, FB.class));
                break;
            case R.id.wa:
                startActivity(new Intent(this, WA.class));
                break;
            case R.id.organo:
                tampilan.setVisibility(View.VISIBLE);
                break;
            case R.id.kkembali:
                tampilan.setVisibility(View.GONE);
                break;
            case R.id.ppk:
                startActivity(new Intent(this, WebPPK.class));
                break;
            case R.id.haccp:
                startActivity(new Intent(this, WebHACCP.class));
                break;
            case R.id.ckib:
                startActivity(new Intent(this, WebCKIB.class));
                break;
            case R.id.bkipm:
                startActivity(new Intent(this, WebBKIPM.class));
                break;
            case R.id.skm:
                startActivity(new Intent(this, WebSKM.class));
                break;
            case R.id.satudata:
                startActivity(new Intent(this, WebSATUDATA.class));
                break;
            case R.id.sampel:
                startActivity(new Intent(this, FormSampel.class));
                break;
            case R.id.satgashpik:
                startActivity(new Intent(this, FileSATGASHPIK.class));
                break;
            case R.id.logout:
                startActivity(new Intent(this, Logout.class));
                break;
            case R.id.alamat:
                startActivity(new Intent(this, Maps.class));
                break;
            case R.id.pnbp:
                startActivity(new Intent(this, TarifPNBP.class));
                break;
            case R.id.seting:
                startActivity(new Intent(this, Setting.class));
                break;
            case R.id.uploadimg:
                startActivity(new Intent(this, UploadImage.class));
                break;
            case R.id.peraturank:
                startActivity(new Intent(this, ListRegulasi.class));
                break;
        }
    }
}