package com.example.gurufinalproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.activity.NoteWriteActivity;
import com.example.gurufinalproject.bean.NoteBean;
import com.example.gurufinalproject.db.FileDB;

import java.util.ArrayList;
import java.util.List;

public class Fragment_2 extends Fragment {
    private ListView mLstNote;
    public ListAdapter adapter;
    public List<NoteBean> noteList = new ArrayList<>();
    private TextView title, sub, detail;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write, container, false);

        // 글작성 버튼
        view.findViewById(R.id.btnWriteW).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NoteWriteActivity.class);
                startActivity(intent);
            }
        });

        mLstNote = view.findViewById(R.id.lstNoteW);

        title = view.findViewById(R.id.txtTitleW);
        sub = view.findViewById(R.id.txtSubTitleW);
        detail = view.findViewById(R.id.txtDetailW);

        title.setText("공공기물훼손 관련 신고페이지 입니다.");
        sub.setText("교내 강의실을 비롯해 이용가능한 공공시설물이 고장, 훼손, 파손 되었을 경우 신고해주십시오. 이용이 불편한 시설물의 위치와 고장사항을 구체적으로 적어주시기 바랍니다.");
        detail.setText("기자재실에서 검토후 빠른 조취를 취할 것 입니다.");

        return view;
    }// end Oncreate


    @Override
    public void onResume() {
        super.onResume();

        noteList = FileDB.getNoteList(getContext());

        adapter = new ListAdapter(noteList, getContext());
        // list view에 adapter 설정
        mLstNote.setAdapter(adapter);
    }

    class ListAdapter extends BaseAdapter {
        List<NoteBean> noteList;
        Context mContext;
        LayoutInflater inflater;

        public ListAdapter(List<NoteBean> noteList, Context context) {
            this.noteList = noteList;
            this.mContext = context;
            this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }

        public void setNoteList(List<NoteBean> noteList) {
            this.noteList = noteList;
        }

        @Override
        public int getCount() {
            return noteList.size();
        }

        @Override
        public Object getItem(int position) {
            return noteList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
           /* view = inflater.inflate(R.layout.note_form,null);

            // 객체획득
            TextView noteTitle = view.findViewById(R.id.noteTitle);
            TextView noteDetail = view.findViewById(R.id.noteDetail);
            TextView noteWriter = view.findViewById(R.id.noteWriter);

            // i번째 객체 획득
            final NoteBean note = noteList.get(i);

            // ui에 적용

            noteTitle.setText(note.noteTitle);
            noteDetail.setText(note.noteDetail);
            noteWriter.setText(note.noteWriter);

            return view;*/
            return null;
        }
    }}
   
// end class
