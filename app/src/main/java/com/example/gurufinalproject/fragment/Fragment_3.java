package com.example.gurufinalproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.bean.NoteBean;

import java.util.ArrayList;
import java.util.List;

public class Fragment_3 extends Fragment {
    private ListView mLstNote;
    public ListAdapter adapter;
    public List<NoteBean> noteList = new ArrayList<>();
    public final static int Saved = 1004;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);

        // 전화걸기 버튼
        Button btnCall = view.findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:029701000"));
                startActivity(i);
            }
        });
        // 글작성 버튼

        // 메인페이지로 가는 버튼

        return view;
    }// end Oncreate

    @Override
    public void onResume() {
        super.onResume();

        //noteList;
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
            convertView = inflater.inflate(R.layout.note_form,null);

            // 객체획득
            ImageView noteImage = convertView.findViewById(R.id.noteImage);
            TextView noteTitle = convertView.findViewById(R.id.noteTitle);
            TextView noteDetail = convertView.findViewById(R.id.noteDetail);
            TextView noteWriter = convertView.findViewById(R.id.noteWriter);
            Button btnDetail = convertView.findViewById(R.id.btnDetail);

            // i번째 객체 획득
            final NoteBean note = noteList.get(position);

            // ui에 적용
            Bitmap bitmap = BitmapFactory.decodeFile(note.notePicPath);
            Bitmap resizeBmp = getResizedBitmap(bitmap, 4, 100, 100);
            noteImage.setImageBitmap(resizeBmp);
            noteTitle.setText(note.noteTitle);
            noteDetail.setText(note.noteDetail);
            noteWriter.setText(note.noteWriter);

            return convertView;
        }
    }

    public static Bitmap getResizedBitmap(Bitmap srcBmp, int size, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = size;
        Bitmap resized = Bitmap.createScaledBitmap(srcBmp, width, height, true);
        return resized;
    }
}// end class
