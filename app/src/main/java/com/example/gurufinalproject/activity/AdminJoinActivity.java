package com.example.gurufinalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.bean.MemberBean;
import com.example.gurufinalproject.db.FileDB;

public class AdminJoinActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String userid;
    private EditText edtName, edtPhone;
    private int userNum ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_join);

        Spinner department = findViewById(R.id.department);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.departments,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        department.setAdapter(adapter);
        department.setOnItemSelectedListener(this);

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
        memberBean.userNum = userNum;

        //관리자 회원가입 후 추가
        FileDB.addAdmin(this,memberBean);
        Toast.makeText(this,"회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    userNum = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }
}
