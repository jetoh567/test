package com.example.gurufinalproject.activity.administrator;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gurufinalproject.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CheckWildAnimalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_wild_animal);

        findViewById(R.id.btnBack4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        AdministratorMainActivity.class);
                startActivity(intent);
            }
        });
    }
}
