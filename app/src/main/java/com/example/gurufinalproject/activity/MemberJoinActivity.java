package com.example.gurufinalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.bean.MemberBean;
import com.example.gurufinalproject.db.FileDB;

public class MemberJoinActivity extends AppCompatActivity {
    private String userid;
    private EditText edtName, edtPhone, edtUserNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_join);

        userid = getIntent().getStringExtra("MemberID");
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhoneNum);
        edtUserNum = findViewById(R.id.edtUserNum);
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
        String name = "" ,phone = "", userNum = "";

        name = edtName.getText().toString();

        if(name.equals("")){
            Toast.makeText(this,"이름을 입력해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }
        memberBean.name = name;

        userNum = edtUserNum.getText().toString();
        if(userNum.equals("")){
            Toast.makeText(this,"학번을 입력해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }
        memberBean.userNum = Integer.parseInt(userNum);

        phone = edtPhone.getText().toString();
        if(phone.equals("")){
            Toast.makeText(this,"번호를 입력해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }
        memberBean.phoneNum = phone;

        //학생 회원가입 후 추가
        FileDB.addMember(this,memberBean);
        Toast.makeText(this,"회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show();
        finish();
    }

}
