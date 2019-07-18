package com.example.gurufinalproject.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.bean.NoteBean;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.net.URL;
import java.util.List;

public class NoteAdapter extends BaseAdapter {

    private Context mContext;
    private List<NoteBean> mNoteList;


    public NoteAdapter(Context context, List<NoteBean> noteList) {
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
        noteDate.setText("신고 날짜 : " + noteBean.regdate);

        if(noteBean.check == false) {
            noteCheck.setText("완료 안됨");
        }else{
            noteCheck.setText("완료 날짜 : "+noteBean.findate);
        }
        return view;
    }
}
