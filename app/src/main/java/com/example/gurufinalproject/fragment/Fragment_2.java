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

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        mLstNote = view.findViewById(R.id.lstNote2);

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
