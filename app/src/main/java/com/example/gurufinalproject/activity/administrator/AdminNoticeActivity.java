package com.example.gurufinalproject.activity.administrator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.activity.LoginActivity;
import com.example.gurufinalproject.activity.NoteAdapter;
import com.example.gurufinalproject.activity.NoteWriteActivity;
import com.example.gurufinalproject.bean.MemberBean;
import com.example.gurufinalproject.bean.NoteBean;
import com.example.gurufinalproject.bean.NoticeBean;
import com.example.gurufinalproject.db.FileDB;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminNoticeActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();
    private ListView mLstNotice;
    public NoticeAdapter mAdapter;
    public List<NoticeBean> noticeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notice);

        TextView txtAdminName = findViewById(R.id.txtAdminNameN);
        TextView txtAdminPart = findViewById(R.id.txtAdminPartN);
        TextView txtAdminNum = findViewById(R.id.txtAdminNumN);

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

        findViewById(R.id.btnNoticeLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
            }
        });

        findViewById(R.id.btnNoticeWrite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), NoticeWriteActivity.class);
                startActivity(i);
            }
        });

        mLstNotice = findViewById(R.id.lstNotice);
        mAdapter = new NoticeAdapter(this,noticeList);
        mLstNotice.setAdapter(mAdapter);

    }// end onCreate

    @Override
    public void onResume() {
        super.onResume();
        mFirebaseDB.getReference().child("Notice").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //데이터 받아와서 리스트에 선언
                noticeList.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    NoticeBean notice = snapshot.getValue(NoticeBean.class);
                   noticeList.add(0,notice);
                }

                //바뀌는 데이터로 refresh 한다.
                if(mAdapter!=null){
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }// end onResume

}
