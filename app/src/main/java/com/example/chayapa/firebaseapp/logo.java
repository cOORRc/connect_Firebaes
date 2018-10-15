package com.example.chayapa.firebaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class logo extends AppCompatActivity {
ImageView logo;
Button btlog,btnew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        logo = (ImageView)findViewById(R.id.imageView2);
        btlog = (Button)findViewById(R.id.log);
        btlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goto_main = new Intent(logo.this,MainActivity.class);
                startActivity(goto_main);
            }
        });

        btnew = (Button)findViewById(R.id.newm);
        btnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goto_newmem = new Intent(logo.this,sign.class);
                startActivity(goto_newmem);
            }
        });

    }
}
