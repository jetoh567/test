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

import androidx.appcompat.app.AppCompatActivity;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.activity.LoginActivity;
import com.example.gurufinalproject.bean.MemberBean;
import com.example.gurufinalproject.bean.NoticeBean;
import com.example.gurufinalproject.db.FileDB;

import java.util.ArrayList;
import java.util.List;

public class AdminNoticeActivity extends AppCompatActivity {

    private ListView mLstNotice;
    public ListAdapter adapter;
    public List<NoticeBean> noticeList = new ArrayList<>();
    public final static int Saved = 1004;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notice);

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

        findViewById(R.id.btnAdminLogout2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnNoticeWrite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NoticeWriteActivity.class);
                startActivityForResult(intent,Saved);
            }
        });

        mLstNotice = findViewById(R.id.lstNotice);

    }// end onCreate

    @Override
    public void onResume() {
        super.onResume();

        noticeList = FileDB.getNoticeList(getApplicationContext());
        //adapter 생성 및 적용
        adapter = new ListAdapter(noticeList, getApplicationContext());
        //list view 에 adapter 설정
        mLstNotice.setAdapter(adapter);
    }// end onResume

    class ListAdapter extends BaseAdapter {
        List<NoticeBean> noticeList; // 원본 데이터
        Context mContext;
        LayoutInflater inflater;

        public ListAdapter( List<NoticeBean> noticeList , Context context){
            this.noticeList = noticeList;
            this.mContext = context;
            this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }

        public void setNoticeList(List<NoticeBean> noticeList){
            //items 갱신을 위한 함수
            this.noticeList = noticeList;
        }

        @Override
        public int getCount() {
            return noticeList.size();
        }

        @Override
        public Object getItem(int position) {
            return noticeList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            convertView = inflater.inflate(R.layout.notice_form,null);

            //객체 획득
            TextView noticeTitle = convertView.findViewById(R.id.NoticeTitle);
            TextView noticeDetail = convertView.findViewById(R.id.NoticeDetail);
            TextView noticeWriter = convertView.findViewById(R.id.NoticeWriter);

            //i 번째 객체 획득
            final NoticeBean notice = noticeList.get(position);

            //ui 에 적용
            noticeTitle.setText(notice.noticeTitle);
            noticeDetail.setText(notice.noticeDetail);
            noticeWriter.setText(notice.noticeWriter);

//            //수정
//            convertView.findViewById(R.id.btnNoticeModify).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(getActivity(),ModifyMemoActivity.class);
//                    intent.putExtra("noticeId", notice.noticeId);
//                    startActivity(intent);
//                }
//            });

            //상세
            convertView.findViewById(R.id.btnNoticeDetail).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),NoticeDetailActivity.class);
                    intent.putExtra("noticeId", notice.noticeId);
                    startActivity(intent);
                }
            });

            return convertView;
        }
    }

}
