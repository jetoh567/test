package com.example.gurufinalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.bean.MemberBean;
import com.example.gurufinalproject.db.FileDB;

import org.w3c.dom.Text;

public class MemberDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_detail);

        TextView txtMemName = findViewById(R.id.txtMemName);
        TextView txtMemId = findViewById(R.id.txtMemId);
        TextView txtMemNum = findViewById(R.id.txtMemNum);

        MemberBean memberBean = FileDB.getLoginMember(getApplicationContext());

        txtMemName.setText("이름 : " + memberBean.name);
        txtMemId.setText("이메일 : " + memberBean.userid);
        txtMemNum.setText("학번 : " + memberBean.userNum );

        Button btnMemWrite = findViewById(R.id.btnMemWrite);
        btnMemWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MemberDetailActivity.this, NotifyActivity.class);
                startActivity(i);
            }
        });

        Button btnMemLogout = findViewById(R.id.btnMemLogout);
        btnMemLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    } // end Oncreate
} // end class
