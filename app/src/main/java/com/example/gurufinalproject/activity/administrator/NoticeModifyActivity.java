package com.example.gurufinalproject.activity.administrator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.bean.MemberBean;
import com.example.gurufinalproject.bean.NoticeBean;
import com.example.gurufinalproject.db.FileDB;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoticeModifyActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private EditText title,detail;
    private TextView writer, date, open;
    private NoticeBean notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_modify);

        title = findViewById(R.id.edtNoticeTitleM);
        detail = findViewById(R.id.edtNoticeDetailM);

        writer = findViewById(R.id.txtNoticeWriterM);
        date = findViewById(R.id.txtNoticeDateM);
        open = findViewById(R.id.txtNoticeOpenM);

        notice = (NoticeBean) getIntent().getSerializableExtra("modify");

        title.setText(notice.noticeTitle);
        detail.setText(notice.noticeDetail);
        writer.setText("작성자 : " + notice.noticeWriter);
        date.setText("등록 날짜 : " + notice.regDate);
        open.setText("조회수 : "+notice.open);

        findViewById(R.id.btnNoticeDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modify();
            }
        });
    }

    private void modify() {
        String check1, check2;
        check1 = title.getText().toString();
        check2 = detail.getText().toString();

        if(check1.equals("")){
            Toast.makeText(this, "제목을 작성해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }

        if(check2.equals("")){
            Toast.makeText(this, "공지글을 작성해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }

        uploadDB();

    }

    private void uploadDB() {

        DatabaseReference dbRef = mFirebaseDatabase.getReference();

        MemberBean loginAdmin = FileDB.getLoginAdmin(this);
        notice.noticeWriter = loginAdmin.name;
        notice.writerid = mFirebaseAuth.getCurrentUser().getEmail();
        notice.noticeTitle = title.getText().toString();
        notice.noticeDetail = detail.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        notice.regDate = sdf.format(new Date());

        dbRef.child("Notice").child(notice.id).setValue(notice);

        Toast.makeText(this,"공지사항이 수정되었습니다.",Toast.LENGTH_SHORT).show();
        finish();
    }

}
