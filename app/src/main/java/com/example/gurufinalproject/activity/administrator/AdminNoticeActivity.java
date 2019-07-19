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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.activity.NoticeDetailActivityM;
import com.example.gurufinalproject.bean.MemberBean;
import com.example.gurufinalproject.bean.NoticeBean;
import com.example.gurufinalproject.db.FileDB;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
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
        txtAdminPart.setText("근무부서 : 상황실");
        txtAdminNum.setText("연락처 : " +memberBean.phoneNum);

        findViewById(R.id.btnNoticeLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoogleSignIn.getClient(getBaseContext(),GoogleSignInOptions.DEFAULT_SIGN_IN).signOut();
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

    public class NoticeAdapter extends BaseAdapter {

        private Context mContext;
        private List<NoticeBean> mNoticeList;


        public NoticeAdapter(Context context, List<NoticeBean> noticeList) {
            mContext = context;
            mNoticeList = noticeList;
        }

        @Override
        public int getCount() {
            return mNoticeList.size();
        }

        @Override
        public Object getItem(int i) {
            return mNoticeList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.notice_form,null);

            TextView title, detail, writer, date;

            title = view.findViewById(R.id.NoticeTitle);
            detail = view.findViewById(R.id.NoticeDetail);
            writer = view.findViewById(R.id.NoticeWriter);
            date = view.findViewById(R.id.NoticeDate);

            final NoticeBean noticeBean = mNoticeList.get(i);

            title.setText(noticeBean.noticeTitle);
            detail.setText(noticeBean.noticeDetail);
            writer.setText("작성자 : "+noticeBean.noticeWriter);
            date.setText("작성 날짜 : "+noticeBean.regDate);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, NoticeDetailActivity.class);
                    i.putExtra("notice",noticeBean);
                    mContext.startActivity(i);
                }
            });

            return view;
        }
    }

}
