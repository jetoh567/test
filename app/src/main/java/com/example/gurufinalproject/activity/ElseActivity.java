package com.example.gurufinalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.gurufinalproject.R;

public class ElseActivity extends AppCompatActivity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_5);

        TextView mtxtSouth = findViewById(R.id.btnSouth);
        TextView mtxtLibrary = findViewById(R.id.btnLibrary);
        TextView mtxtSharlom = findViewById(R.id.btnSharlom);
        mtxtSouth.setOnClickListener(mBtnClick);
        mtxtLibrary.setOnClickListener(mBtnClick);
        mtxtSharlom.setOnClickListener(mBtnClick);

        mtxtSouth.setText(Html.fromHtml("<font color=\"#2a7de2\"><u>02-970-5185</u></font>"));
        mtxtLibrary.setText(Html.fromHtml("<font color=\"#2a7de2\"><u>02-970-5191</u></font>"));
        mtxtSharlom.setText(Html.fromHtml("<font color=\"#2a7de2\"><u>02-970-5194</u></font>"));
    }

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
}
