package com.example.gurufinalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.bean.MemberBean;

public class AdminJoinActivity extends AppCompatActivity {

    private String userid;
    private EditText edtName, edtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_join);

        userid = getIntent().getStringExtra("AdminID");
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhoneNum);
        TextView txtId = findViewById(R.id.txtGoogleId);

        txtId.setText("Google Id : " + userid);

        findViewById(R.id.btnCreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joinProcess();
            }
        });


    }

    private void joinProcess(){
        MemberBean memberBean = new MemberBean();
        memberBean.userid = userid;
        String name = "" ,phone = "";

        name = edtName.getText().toString();

        if(name.equals("")){
            Toast.makeText(this,"이름을 입력해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }
        memberBean.name = name;

        phone = edtPhone.getText().toString();
        if(phone.equals("")){
            Toast.makeText(this,"번호를 입력해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }
        memberBean.phoneNum = phone;

    }
}
