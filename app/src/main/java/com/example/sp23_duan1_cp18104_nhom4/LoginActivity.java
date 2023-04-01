package com.example.sp23_duan1_cp18104_nhom4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.sp23_duan1_cp18104_nhom4.Fragment.doiMkFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {
    TextView dk,quenmk,sKip;
    Button btn_dangnhap;
    EditText edEmail,edPassword;
    FirebaseAuth auth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("ĐĂNG NHẬP");

        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        dk = findViewById(R.id.dk);
        quenmk = findViewById(R.id.tvquenmk);
        btn_dangnhap = findViewById(R.id.btnLogin);
        sKip = findViewById(R.id.skip);
        sKip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentskip = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intentskip);
            }
        });
        auth = FirebaseAuth.getInstance();
        quenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new doiMkFragment();
                fragment.getActivity();


            }
        });
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = edEmail.getText().toString();
                final String password = edPassword.getText().toString();
                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập địa chỉ Email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(Email,password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        edPassword.setError("Password quá ngắn, tối thiểu phải 6 kí tự!");
                                    } else {
                                        Toast.makeText(LoginActivity.this,"Đăng nhập thất bại, vui lòng kiểm tra lại thông tin!" , Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent1 = new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(intent1);
                                    finish();
                                }
                            }});
            }
        });
        dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SigUpActivity.class);
                startActivity(intent);
            }
        });
    }

//

}