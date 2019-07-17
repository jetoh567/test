package com.example.gurufinalproject.activity.administrator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.activity.LoginActivity;
import com.example.gurufinalproject.bean.MemberBean;
import com.example.gurufinalproject.db.FileDB;

public class AdminCheckWritingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_check_writing);

        TextView txtAdminName = findViewById(R.id.txtAdminName);
        TextView txtAdminPart = findViewById(R.id.txtAdminPart);
        TextView txtAdminNum = findViewById(R.id.txtAdminNum);

        MemberBean memberBean = FileDB.getLoginAdmin(this);

        String department ="" ;
        txtAdminName.setText("이름 : " + memberBean.name);
        switch(memberBean.userNum){
            case 0:
                department = "상황실";
                break;
            case 1:
                department = "학생 지원실";
                break;
            case 2:
                department = "기관실";
                break;
            case 3:
                department = "경비실";
                break;
        }
        txtAdminPart.setText("근무부서 : " + department);
        txtAdminNum.setText("연락처 : " +memberBean.phoneNum);

        findViewById(R.id.btnAdminLogout1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
