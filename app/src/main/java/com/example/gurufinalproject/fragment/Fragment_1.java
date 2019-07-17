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

public class Fragment_1 extends Fragment {
    private TextView title, sub, detail;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call, container, false);

        // 전화걸기 버튼
        Button btnCall = view.findViewById(R.id.btnCallC);
        title = view.findViewById(R.id.txtTitleC);
        sub = view.findViewById(R.id.txtSubTitleC);
        detail = view.findViewById(R.id.txtDetailC);

        title.setText("외부칩입 관련 \n신고페이지 입니다.");
        sub.setText("교내에서 신원이 불분명하거나 \n수상한 행동을 하는 외부인을 \n목격 및 발견 했을 경우 신고해주십시오.");
        detail.setText("전화를 걸 경우, \n02-970-1000 번으로 연락이 갑니다. \n종합상황실에서 빠르게 대응할 것 입니다.");

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:029071000"));
                startActivity(intent);
            }
        });

        return view;
    }// end Oncreate

}// end class
