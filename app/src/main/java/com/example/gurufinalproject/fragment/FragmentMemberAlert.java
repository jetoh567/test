package com.example.gurufinalproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.bean.NoteBean;

import java.util.ArrayList;
import java.util.List;

public class FragmentMemberAlert extends Fragment {
    private ListView listView;
    private ListAdapter adapter;
    public List<NoteBean> noteList = new ArrayList<>();

    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_alert, container, false);

        return view;
    }

}
