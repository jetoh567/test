package com.example.gurufinalproject.fragment;

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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.activity.NoticeDetailActivityM;
import com.example.gurufinalproject.activity.administrator.NoticeDetailActivity;
import com.example.gurufinalproject.bean.NoticeBean;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentMemberAlert extends Fragment {
    private FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();
    private ListView mLstNotice;
    public NoticeAdapterM mAdapter;
    public List<NoticeBean> noticeList = new ArrayList<>();

    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_alert, container, false);

        mLstNotice = view.findViewById(R.id.lstAlertView);
        mAdapter = new NoticeAdapterM(getContext(),noticeList);
        mLstNotice.setAdapter(mAdapter);

        return view;
    }

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

}
    public class NoticeAdapterM extends BaseAdapter {

        private Context mContext;
        private List<NoticeBean> mNoticeList;


        public NoticeAdapterM(Context context, List<NoticeBean> noticeList) {
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
                    Intent i = new Intent(mContext, NoticeDetailActivityM.class);
                    i.putExtra("notice",noticeBean);
                    mContext.startActivity(i);
                }
            });

            return view;
        }
    }

}
