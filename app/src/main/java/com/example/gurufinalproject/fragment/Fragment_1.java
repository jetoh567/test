package com.example.gurufinalproject.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.bean.NoteBean;

import java.util.ArrayList;
import java.util.List;

public class Fragment_1 extends Fragment {
    private ListView mLstNote;
    public final static int Saved = 1004;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        // 전화걸기 버튼
        //view.findViewById(R.id)

        // 메인페이지로 가는 버튼

        return view;
    }// end Oncreate

}// end class
