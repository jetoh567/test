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

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoticeWriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_write);

        findViewById(R.id.btnNoticeCancel).setOnClickListener(mBtnClick);
        findViewById(R.id.btnNoticeComplete).setOnClickListener(mBtnClick);

    }

    private View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch(view.getId()){

                case R.id.btnNoticeCancel:
                    finish();
                    break;

                case R.id.btnNoticeComplete:
                    saveProc();
                    break;
            }

        }
    };

    private void saveProc(){
        String titleStr = "";
        String writingStr = "";

        EditText EdtNoticeTitle = findViewById(R.id.EdtNoticeTitle);
        EditText EdtNoticeWriting = findViewById(R.id.EdtNoticeWriting);
        titleStr = EdtNoticeTitle.getText().toString();
        writingStr = EdtNoticeWriting.getText().toString();

        NoticeBean noticeBean = new NoticeBean();

        if(titleStr.equals("")){
            Toast.makeText(this,"제목를 작성해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }

        if(writingStr.equals("")){
            Toast.makeText(this,"공지사항을 작성해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }

        MemberBean loginAdmin = FileDB.getLoginAdmin(this);
        noticeBean.noticeTitle = titleStr;
        noticeBean.noticeDetail = titleStr;

        FileDB.addNotice(this, loginAdmin.userid, noticeBean);

        Toast.makeText(this,"메모가 추가되었습니다.",Toast.LENGTH_SHORT).show();

        finish();
    }


}
