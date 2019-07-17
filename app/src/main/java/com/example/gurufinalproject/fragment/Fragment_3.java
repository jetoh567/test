package com.example.gurufinalproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class Fragment_3 extends Fragment {
    private ListView mLstNote;
    public ListAdapter adapter;
    public List<NoteBean> noteList = new ArrayList<>();
    private TextView title, sub, detail;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_both, container, false);

        mLstNote = view.findViewById(R.id.lstNoteB);

        title = view.findViewById(R.id.txtTitleB);
        sub = view.findViewById(R.id.txtSubTitleB);
        detail = view.findViewById(R.id.txtdetailB);

        // 전화걸기 버튼
        Button btnCall = view.findViewById(R.id.btnCallB);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:029701000"));
                startActivity(i);
            }
        });

        // 글작성 버튼
            view.findViewById(R.id.btnWriteB).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), NoteWriteActivity.class);
                    startActivity(intent);
                }
            });

            title.setText("야생동물 관련 \n신고페이지 입니다.");
            sub.setText("교내에서 야생멧돼지나 \n고라니를 비롯한 야생동물을 \n목격했을 경우 신고해주십시오. \n야생동물을 목격한 위치를 구체적으로 적어주시기 바랍니다.");
            detail.setText("전화를 걸 경우, \n02-970-1000 번으로 연락이 갑니다. \n종합상황실에서 검토후 빠르게 대응할 것 입니다.");

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
