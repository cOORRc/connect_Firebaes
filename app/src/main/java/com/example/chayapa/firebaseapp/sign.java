package com.example.chayapa.firebaseapp;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    TextView TVreg;
    ImageView IMreg;
    EditText Temail,Tpass;
    Button regis;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);


        firebaseAuth = FirebaseAuth.getInstance();
        Temail = (EditText) findViewById(R.id.ETemail);
        Tpass = (EditText)findViewById(R.id.ETpass);

        progressDialog = new ProgressDialog(this);

        IMreg = (ImageView)findViewById(R.id.imlogo);
        TVreg = (TextView)findViewById(R.id.tvregis);

        regis = (Button)findViewById(R.id.submit);
        regis.setOnClickListener(new View.OnClickListener() { //สร้างฟังชันก์รอกดปุ่ม
            @Override
            public void onClick(View v) {
                //คลิกแล้วให้ทำอะไร สิ่งที่จะให้มันทำตอนกดปุ่ม
                registerUser();
            }
        });
    }

    private void registerUser() {
        String email = Temail.getText().toString().trim();//trim = กำหนดไม่ให้มีช่องว่างในการกรองเมลล์
        String pass = Tpass.getText().toString().trim();//trim = กำหนดไม่ให้มีช่องว่างในการกรองเมลล์

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Pleass enter email",Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Pleass enter password",Toast.LENGTH_LONG)
                    .show();
            return;
        }

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

//creating a new user
        // เรียกใช้ฟังชัน และส่งข้อมูล email กับ  paas ไปเก็บที่ firebase
        firebaseAuth.signInWithEmailAndPassword(email, pass)
        //firebaseAuth.createUserWithEmailAndPassword(email, pass)    //singinwith
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {  //เมื่อส่งข้อมูลไป firebase
                    // รอจน firebase ทำเสร็จแล้วทำตามเงื่อนไข
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            Toast.makeText(sign.this,"Successfully registered",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(sign.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();  //ปิดไดอะล็อก
                    }
                });
    }

}
