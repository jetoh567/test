package com.example.gurufinalproject.activity.administrator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gurufinalproject.R;

public class AdministratorMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_administrator_main);
    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.activity_administrator_main,container,false);
//
//        TextView txtMemName = view.findViewById(R.id.txtMemName);
//        TextView txtMemPart = view.findViewById(R.id.txtMemPart);
//        TextView txtMemNum = view.findViewById(R.id.txtMemNum);
//
//        MemberBean memberBean = FileDB.getLoginMember(getActivity());
//
//        txtMemName.setText("이름 : " +memberBean.memName);
//        txtMemPart.setText("근무부서 : " + memberBean.mempass);
//        txtMemNum.setText("연락처 : " +memberBean.memName);
//        txtMemDate.setText("가입날짜 " + memberBean.memDate);
//
//        view.findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getActivity().finish();
//
//            }
//        });
//
//        return view;
//    }


}
