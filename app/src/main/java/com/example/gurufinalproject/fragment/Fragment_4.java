package com.example.gurufinalproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.activity.NoteWriteActivity;
import com.example.gurufinalproject.bean.NoteBean;
import com.example.gurufinalproject.db.FileDB;

import java.util.ArrayList;
import java.util.List;

public class Fragment_4 extends Fragment {
    private ListView mLstNote;
    public ListAdapter adapter;
    public List<NoteBean> noteList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_4, container, false);

        mLstNote = view.findViewById(R.id.lstNote4);

        return view;
        // 메인페이지로 가는 버튼
    }// end Oncreate

    @Override
    public void onResume() {
        super.onResume();

        noteList = FileDB.getNoteList(getContext());
        // adapter 생성 및 적용
        adapter = new ListAdapter(noteList,getContext());
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            return null;
        }
    }

}// end class
