package com.example.gurufinalproject.activity.administrator;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gurufinalproject.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CheckLostArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_lost_article);

        findViewById(R.id.btnBack2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        AdministratorMainActivity.class);
                startActivity(intent);
            }
        });

    }
}
