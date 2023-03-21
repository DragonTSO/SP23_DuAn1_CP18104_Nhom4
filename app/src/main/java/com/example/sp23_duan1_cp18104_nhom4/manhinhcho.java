package com.example.sp23_duan1_cp18104_nhom4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class manhinhcho extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhcho);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    Intent intent  = new Intent(manhinhcho.this,LoginActivity.class);
                startActivity(intent);
                finish();

            }
        },1000 );
    }

}