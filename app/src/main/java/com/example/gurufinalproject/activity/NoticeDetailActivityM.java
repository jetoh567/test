package com.example.gurufinalproject.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.bean.NoticeBean;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NoticeDetailActivityM extends AppCompatActivity {

    private  TextView title, detail, writer, date, open;
    private FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail_member);

        title = findViewById(R.id.txtNoiceTitleMe);
        detail = findViewById(R.id.txtNoticeDetailMe);
        writer = findViewById(R.id.txtNoticeWriterMe);
        date = findViewById(R.id.txtNoticeDateMe);
        open = findViewById(R.id.txtNoticeOpenMe);

        final NoticeBean notice  = (NoticeBean) getIntent().getSerializableExtra("noticeM");

        title.setText(notice.noticeTitle);
        detail.setText(notice.noticeDetail);
        writer.setText("작성자 : " + notice.noticeWriter);
        date.setText("등록 날짜 : " +notice.regDate);
        open.setText("조회수 : " + notice.open);

        DatabaseReference dbRef = mFirebaseDB.getReference();
        notice.open += 1;
        dbRef.child("Notice").child(notice.id).setValue(notice);
    }

}
