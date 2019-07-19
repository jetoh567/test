package com.example.gurufinalproject.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.activity.ElseActivity;
import com.example.gurufinalproject.activity.NoteWriteActivity;
import com.example.gurufinalproject.activity.NoticeDetailActivityM;
import com.example.gurufinalproject.bean.NoticeBean;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FragmentMemberMain extends Fragment {

    private Intent intent;
    private FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();
    private TextView alert;

    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_main, container, false);

        view.findViewById(R.id.btn_outsider).setOnClickListener(mBtnClick);
        view.findViewById(R.id.btn_public).setOnClickListener(mBtnClick);
        view.findViewById(R.id.btn_lost).setOnClickListener(mBtnClick);
        view.findViewById(R.id.btn_animal).setOnClickListener(mBtnClick);
        view.findViewById(R.id.btn_emergency).setOnClickListener(mBtnClick);
        view.findViewById(R.id.btn_aso).setOnClickListener(mBtnClick);
        alert = view.findViewById(R.id.txtAlert);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mFirebaseDB.getReference().child("Notice").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                NoticeBean notice = null;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    notice = snapshot.getValue(NoticeBean.class);
                }
                if(notice == null){
                    alert.setText("새로운 공지사항이 없습니다.");
                }else{
                    alert.setText(notice.noticeTitle);
                }
                final NoticeBean noticeBean = notice;
                if(noticeBean!=null){
                    alert.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getContext(), NoticeDetailActivityM.class);
                            i.putExtra("noticeM",noticeBean);
                            startActivity(i);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_outsider:
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:029701000"));
                    startActivity(intent);
                    break;
                case R.id.btn_public:
                    intent = new Intent(getActivity(), NoteWriteActivity.class);
                    intent.putExtra("department",2);
                    startActivity(intent);
                    break;
                case R.id.btn_lost:
                    intent = new Intent(getActivity(),NoteWriteActivity.class);
                    intent.putExtra("department",1);
                    startActivity(intent);
                    break;
                case R.id.btn_animal:
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:029701000"));
                    startActivity(intent);
                    break;
                case R.id.btn_emergency:
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:029701000"));
                    startActivity(intent);
                    break;
                case R.id.btn_aso:
                    intent = new Intent(getActivity(), ElseActivity.class);
                    startActivity(intent);
            }
        }
    };
}
