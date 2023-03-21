package com.example.sp23_duan1_cp18104_nhom4;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigUpActivity extends AppCompatActivity {
    EditText dkEmail,dkPassword,dkrePassword;
    Button btnSigUp;
    TextView dn;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sig_up);

        auth = FirebaseAuth.getInstance();

        btnSigUp =(Button) findViewById(R.id.btnSigup);
        dkEmail = findViewById(R.id.dkEmail);
        dkPassword = findViewById(R.id.dkPassword);
        dkrePassword = findViewById(R.id.dkrePassword);
        dn = findViewById(R.id.dn);

        dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SigUpActivity.this, LoginActivity.class));
            }
        });
        btnSigUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = dkEmail.getText().toString().trim();
                String pass  = dkPassword.getText().toString().trim();
                String repass = dkrePassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập email",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập mật khẩu",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(repass)){
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập lại mật khẩu",Toast.LENGTH_LONG).show();
                    return;
                }
                if(pass.length() < 6){
                    Toast.makeText(getApplicationContext(), "Mật khẩu quá ngắn, Tối thiểu phải 6 kí tự!", Toast.LENGTH_SHORT).show();
                    return;
                }


                auth.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(SigUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SigUpActivity.this,"Đăng ký thành công!"+task.isSuccessful(),Toast.LENGTH_LONG).show();
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SigUpActivity.this, "Đăng ký thất bại!" + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(SigUpActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}