package com.example.gurufinalproject.activity.administrator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.bean.NoticeBean;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NoticeDetailActivity extends AppCompatActivity {

    private  TextView title, detail, writer, date, open;
    private FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);

        title = findViewById(R.id.txtNoiceTitle);
        detail = findViewById(R.id.txtNoticeDetail);
        writer = findViewById(R.id.txtNoticeWriter);
        date = findViewById(R.id.txtNoticeDate);
        open = findViewById(R.id.txtNoticeOpen);

        final NoticeBean notice  = (NoticeBean) getIntent().getSerializableExtra("notice");

        title.setText(notice.noticeTitle);
        detail.setText(notice.noticeDetail);
        writer.setText("작성자 : " + notice.noticeWriter);
        date.setText("등록 날짜 : " +notice.regDate);
        open.setText("조회수 : " + notice.open);

        DatabaseReference dbRef = mFirebaseDB.getReference();
        notice.open += 1;
        dbRef.child("Notice").child(notice.id).setValue(notice);

        findViewById(R.id.btnNoticeModify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getBaseContext(), NoticeModifyActivity.class);
                i.putExtra("modify",notice);
                startActivity(i);
                finish();
            }
        });

    }

}
