package com.example.gurufinalproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gurufinalproject.R;

public class FragmentMemberMain extends Fragment {

    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_main, container, false);


        view.findViewById(R.id.btn_outsider).setOnClickListener(mBtnClick);
        view.findViewById(R.id.btn_public).setOnClickListener(mBtnClick);
        view.findViewById(R.id.btn_lost).setOnClickListener(mBtnClick);
        view.findViewById(R.id.btn_animal).setOnClickListener(mBtnClick);
        view.findViewById(R.id.btn_else).setOnClickListener(mBtnClick);
        view.findViewById(R.id.btn_emergency).setOnClickListener(mBtnClick);

        return view;
    }

    View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){

            }
        }
    };
}
