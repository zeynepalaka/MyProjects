package com.example.tolistproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText RegEmail,RegPwd;
    private Button RegBtn;
    private TextView RegQn;
    private FirebaseAuth mAuth;

    private ProgressDialog loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        toolbar=findViewById(R.id.loginToolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");

        mAuth=FirebaseAuth.getInstance();
        loader=new ProgressDialog(this);


        RegEmail=findViewById(R.id.RegisterEmail);
        RegPwd=findViewById(R.id.RegisterPassword);
        RegBtn=findViewById(R.id.RegisterButton);
        RegQn=findViewById(R.id.RegisterPageQuestion);
        RegQn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        RegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String email=RegEmail.getText().toString().trim();
                 String password=RegPwd.getText().toString().trim();
                 if(TextUtils.isEmpty(email)){
                     RegEmail.setError("E-mail gerekli!");
                     return;
                 }
                if(TextUtils.isEmpty(password)){
                    RegPwd.setError("Şifre gerekli!");
                    return;
                }else {
                    loader.setMessage("Kayıt ilerlemesi.");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Intent intent=new Intent(RegisterActivity.this,HomeActivity.class);
                                startActivity(intent);
                                finish();
                                loader.dismiss();
                            }else{
                                String error=task.getException().toString();
                                Toast.makeText(RegisterActivity.this,"Kayıt başarısız oldu."+error,Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }
                        }
                    });
                }


            }
        });

    }


}