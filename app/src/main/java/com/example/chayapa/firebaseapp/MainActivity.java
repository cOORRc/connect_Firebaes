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

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    TextView TVwel,TVlogin;
    ImageView logo;
    EditText jemail,jpass;
    Button submit;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        jemail = (EditText) findViewById(R.id.ETemail);
        jpass = (EditText)findViewById(R.id.ETpass);

        progressDialog = new ProgressDialog(this);

        TVwel = (TextView)findViewById(R.id.tvwel);
        logo = (ImageView)findViewById(R.id.imlogo);
        TVlogin = (TextView)findViewById(R.id.tvregis);

        submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() { //สร้างฟังชันก์รอกดปุ่ม
            @Override
            public void onClick(View v) {
                //คลิกแล้วให้ทำอะไร สิ่งที่จะให้มันทำตอนกดปุ่ม
                registerUser();
            }
        });


}

    private void registerUser() {
        String email = jemail.getText().toString().trim();//trim = กำหนดไม่ให้มีช่องว่างในการกรองเมลล์
        String pass = jpass.getText().toString().trim();//trim = กำหนดไม่ให้มีช่องว่างในการกรองเมลล์

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

        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

//creating a new user
        // เรียกใช้ฟังชัน และส่งข้อมูล email กับ  paas ไปเก็บที่ firebase
        firebaseAuth.createUserWithEmailAndPassword(email, pass)    //singinwith
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {  //เมื่อส่งข้อมูลไป firebase
                    // รอจน firebase ทำเสร็จแล้วทำตามเงื่อนไข
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(MainActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();  //ปิดไดอะล็อก
                    }
                });
    }
}
