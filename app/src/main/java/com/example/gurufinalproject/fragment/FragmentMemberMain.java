package com.example.gurufinalproject.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.activity.ElseActivity;
import com.example.gurufinalproject.activity.NoteWriteActivity;

public class FragmentMemberMain extends Fragment {

    private Intent intent;

    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_main, container, false);

        view.findViewById(R.id.btn_outsider).setOnClickListener(mBtnClick);
        view.findViewById(R.id.btn_public).setOnClickListener(mBtnClick);
        view.findViewById(R.id.btn_lost).setOnClickListener(mBtnClick);
        view.findViewById(R.id.btn_animal).setOnClickListener(mBtnClick);
        view.findViewById(R.id.btn_emergency).setOnClickListener(mBtnClick);
        view.findViewById(R.id.btn_else).setOnClickListener(mBtnClick);

        return view;
    }

    View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_outsider:
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:029701000"));
                    startActivity(intent);
                    break;
                case R.id.btn_public:
                    intent = new Intent(getActivity(), NoteWriteActivity.class);
                    intent.putExtra("department",2);
                    startActivity(intent);
                    break;
                case R.id.btn_lost:
                    intent = new Intent(getActivity(),NoteWriteActivity.class);
                    intent.putExtra("department",1);
                    startActivity(intent);
                    break;
                case R.id.btn_animal:
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:029701000"));
                    startActivity(intent);
                    break;
                case R.id.btn_emergency:
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:029701000"));
                    startActivity(intent);
                    break;
                case R.id.btn_else:
                    intent = new Intent(getActivity(), ElseActivity.class);
                    startActivity(intent);
            }
        }
    };
}
