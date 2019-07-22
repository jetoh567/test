package com.example.gurufinalproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.bean.MemberBean;
import com.example.gurufinalproject.db.FileDB;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class FragmentMemberDetail extends Fragment {

    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_detail, container, false);


        TextView txtMemName = view.findViewById(R.id.txtMemName);
        TextView txtMemId = view.findViewById(R.id.txtMemId);
        TextView txtMemNum = view.findViewById(R.id.txtMemNum);

        MemberBean memberBean = FileDB.getLoginMember(getContext());

        txtMemName.setText(memberBean.name);
        txtMemId.setText(memberBean.userid);
        txtMemNum.setText(memberBean.userNum );

        Button btnMemLogout = view.findViewById(R.id.btnMemLogout);
        btnMemLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GoogleSignIn.getClient(getContext(), GoogleSignInOptions.DEFAULT_SIGN_IN).signOut();
                getActivity().finish();
            }
        });

        return view;
    }

}
