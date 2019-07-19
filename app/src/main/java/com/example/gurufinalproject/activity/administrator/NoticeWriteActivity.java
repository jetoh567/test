package com.example.gurufinalproject.activity.administrator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.bean.MemberBean;
import com.example.gurufinalproject.bean.NoticeBean;
import com.example.gurufinalproject.db.FileDB;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoticeWriteActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private EditText mEdtTitle, mEdtdetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_write);

        findViewById(R.id.btnNoticeCancel).setOnClickListener(mBtnClick);
        findViewById(R.id.btnNoticeComplete).setOnClickListener(mBtnClick);

        mEdtTitle = findViewById(R.id.edtNoticeTitle);
        mEdtdetail = findViewById(R.id.edtNoticeDetail);

    }

    private View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch(view.getId()){

                case R.id.btnNoticeCancel:
                    finish();
                    break;

                case R.id.btnNoticeComplete:
                    upload();
                    break;
            }

        }
    };

    private void upload() {

        String title, detail;

        title = mEdtTitle.getText().toString();
        detail = mEdtdetail.getText().toString();

        if(title.equals("")){
            Toast.makeText(this, "제목을 작성해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }

        if(detail.equals("")){
            Toast.makeText(this, "공지글을 작성해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }

        uploadDB();

    }
    private void uploadDB() {

        DatabaseReference dbRef = mFirebaseDatabase.getReference();
        String id = dbRef.push().getKey(); // key 를 메모의 고유 ID 로 사용한다.

        //데이터 베이스에 저장한다.
        NoticeBean noticeBean = new NoticeBean();

        noticeBean.id = id;
        noticeBean.writerid = mFirebaseAuth.getCurrentUser().getEmail();
        noticeBean.noticeTitle = mEdtTitle.getText().toString();
        noticeBean.noticeDetail = mEdtdetail.getText().toString();

        MemberBean loginAdmin = FileDB.getLoginAdmin(this);
        noticeBean.noticeWriter = loginAdmin.name;

        noticeBean.open = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        noticeBean.regDate = sdf.format(new Date());

        dbRef.child("Notice").child(noticeBean.id).setValue(noticeBean);

        Toast.makeText(this,"공지사항이 등록되었습니다.",Toast.LENGTH_SHORT).show();
        finish();
    }
}
