package com.example.gurufinalproject.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.activity.ElseActivity;
import com.example.gurufinalproject.activity.NoteWriteActivity;

public class Fragment_5 extends Fragment {
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_5, container, false);

        TextView mtxtSouth = view.findViewById(R.id.btnSouth);
        TextView mtxtLibrary = view.findViewById(R.id.btnLibrary);
        TextView mtxtSharlom = view.findViewById(R.id.btnSharlom);
        mtxtSouth.setOnClickListener(mBtnClick);
        mtxtLibrary.setOnClickListener(mBtnClick);
        mtxtSharlom.setOnClickListener(mBtnClick);

        mtxtSouth.setText(Html.fromHtml("<font color=\"#2a7de2\"><u>02-970-5185</u></font>"));
        mtxtLibrary.setText(Html.fromHtml("<font color=\"#2a7de2\"><u>02-970-5191</u></font>"));
        mtxtSharlom.setText(Html.fromHtml("<font color=\"#2a7de2\"><u>02-970-5194</u></font>"));

        return view;
    }// end Oncreate

    View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnSouth:
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:029705185"));
                    startActivity(intent);
                    break;
                case R.id.btnLibrary:
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:029705191"));
                    startActivity(intent);
                    break;
                case R.id.btnSharlom:
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:029705194"));
                    startActivity(intent);
                    break;
            }
        }
    };


}// end class
