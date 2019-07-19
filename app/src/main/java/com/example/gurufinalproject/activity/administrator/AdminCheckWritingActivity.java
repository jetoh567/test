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
import com.example.gurufinalproject.bean.MemberBean;
import com.example.gurufinalproject.bean.NoteBean;
import com.example.gurufinalproject.db.FileDB;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminCheckWritingActivity extends AppCompatActivity {

    private MemberBean memberBean;
    private TextView name, department, number;
    private FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();
    private ListView mLstNote;
    public NoteAdminAdapter mAdapter;
    public List<NoteBean> noteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_check_writing);

        mLstNote = findViewById(R.id.noteList);

        mAdapter = new NoteAdminAdapter(this,noteList);
        mLstNote.setAdapter(mAdapter);

        name = findViewById(R.id.txtAdminName);
        department = findViewById(R.id.txtAdminPart);
        number = findViewById(R.id.txtAdminNum);

        memberBean = FileDB.getLoginAdmin(this);

        name.setText("이름 : " + memberBean.name);
        switch(memberBean.userNum){
            case 0:
                department.setText("근무부서 : 상황실");
                break;
            case 1:
                department.setText("근무부서 : 학생 지원실");
                break;
            case 2:
                department.setText("근무부서 : 기관실");
                break;
            case 3:
                department.setText("근무부서 : 경비실");
                break;
        }
        number.setText("연락처 : "+memberBean.phoneNum);


        findViewById(R.id.btnAdminLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        mFirebaseDB.getReference().child("Note").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //데이터 받아와서 리스트에 선언
                noteList.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    NoteBean note = snapshot.getValue(NoteBean.class);

                    switch (memberBean.userNum){
                        case 1:
                            if(note.department == 1){
                                noteList.add(0,note);
                            }
                            break;
                        case 2:
                            if(note.department == 2){
                                noteList.add(0,note);
                            }
                            break;
                        case 3:
                            if(note.department == 3){
                                noteList.add(0,note);
                            }
                            break;
                    }
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

    public class NoteAdminAdapter extends BaseAdapter {

        private Context mContext;
        private List<NoteBean> mNoteList;


        public NoteAdminAdapter(Context context, List<NoteBean> noteList) {
            mContext = context;
            mNoteList = noteList;
        }

        @Override
        public int getCount() {
            return mNoteList.size();
        }

        @Override
        public Object getItem(int i) {
            return mNoteList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.note_form,null);
            TextView noteTitle = view.findViewById(R.id.noteLocation);
            TextView noteDetail = view.findViewById(R.id.noteDetail);
            TextView noteDate = view.findViewById(R.id.noteDate);
            TextView noteCheck = view.findViewById(R.id.noteCheck);

            final NoteBean noteBean = mNoteList.get(i);

            //imgTitle 이미지를 표시할 때는 원격서버에 있는 이미지임으로 비동기로 표시한다.
            noteDetail.setText("신고 내용 : " + noteBean.detail);
            noteTitle.setText("신고 위치 : " + noteBean.location);
            noteDate.setText("신고 날짜 : " + noteBean.regdate);    if(noteBean.check == false) {
                noteCheck.setText("완료 안됨");
            }else{
                noteCheck.setText("완료 날짜 : "+noteBean.findate);
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, NoteAdminActivity.class);
                    i.putExtra("detailA",noteBean);
                    mContext.startActivity(i);
                }
            });
            return view;
        }
    }

}
