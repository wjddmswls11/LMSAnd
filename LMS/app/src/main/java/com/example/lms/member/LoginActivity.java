package com.example.lms.member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lms.MainActivity;
import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    EditText edtid, edtpw;
    Button edtlogin;
    LinearLayout login_lin2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtid = findViewById(R.id.edtid);
        edtpw = findViewById(R.id.edtpw);
        edtlogin = findViewById(R.id.edtlogin);
        login_lin2 = findViewById(R.id.login_lin2);

        login_lin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });

        edtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonAskTask task = new CommonAskTask("andLogin", LoginActivity.this);
                task.addParam("id", edtid.getText());
                task.addParam("pw", edtpw.getText());
                task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {

                        if(data == "다시 입력"){
                            Toast.makeText(LoginActivity.this, "아이디/비번이 잘못되었습니다", Toast.LENGTH_SHORT).show();
                        }else{
                            MemberVO vo = new Gson().fromJson(data, MemberVO.class);
                            if(vo == null) {
                                Toast.makeText(LoginActivity.this, "vo값이 없습니다", Toast.LENGTH_SHORT).show();
                            }else {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

            }
        });








    }







    
    
}