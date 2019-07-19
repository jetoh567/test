package com.example.gurufinalproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.activity.administrator.AdminNoticeActivity;
import com.example.gurufinalproject.activity.administrator.NoticeAdapter;
import com.example.gurufinalproject.bean.NoteBean;
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
    public NoticeAdapter mAdapter;
    public List<NoticeBean> noticeList = new ArrayList<>();

    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_alert, container, false);

        mLstNotice = view.findViewById(R.id.lstAlertView);
        mAdapter = new NoticeAdapter(getContext(),noticeList);
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
}
