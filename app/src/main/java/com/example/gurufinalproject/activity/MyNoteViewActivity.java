package com.example.gurufinalproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.bean.NoteBean;
import com.example.gurufinalproject.db.FileDB;

import java.util.ArrayList;
import java.util.List;

public class MyNoteViewActivity extends AppCompatActivity {
    private ListView listView;
    private ListAdapter adapter;
    public List<NoteBean> noteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_note_view);

        findViewById(R.id.btnBackMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    } // end Oncreate

} // end class
