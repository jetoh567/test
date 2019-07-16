package com.example.gurufinalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gurufinalproject.R;

public class MemberMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_main);

        findViewById(R.id.btn_info).setOnClickListener(mBtnClick);
    }

    View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_info:
                    Intent i = new Intent(getBaseContext(),MemberDetailActivity.class);
                    startActivity(i);
                    break;
            }
        }
    };
}
