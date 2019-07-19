package com.example.gurufinalproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.activity.NoteAdapter;
import com.example.gurufinalproject.activity.NoteWriteActivity;
import com.example.gurufinalproject.bean.NoteBean;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Fragment_3 extends Fragment {
    private FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();
    private ListView mLstNote;
    public NoteAdapter mAdapter;
    public List<NoteBean> noteList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);

        mLstNote = view.findViewById(R.id.lstNote3);
        mAdapter = new NoteAdapter(getContext(),noteList);
        mLstNote.setAdapter(mAdapter);


        // 글작성 버튼
            view.findViewById(R.id.btnWrite3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), NoteWriteActivity.class);
                    intent.putExtra("department",3);
                    startActivity(intent);
                }
            });

            return view;
        }// end Oncreate


    @Override
    public void onResume() {
        super.onResume();


        mFirebaseDB.getReference().child("Note").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //데이터 받아와서 리스트에 선언
                noteList.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    NoteBean note = snapshot.getValue(NoteBean.class);
                    if(note.department == 3 && note.access == false){
                        noteList.add(note);
                    }
                }

                //바뀌는 데이터로 refresh 한다.
                if(mAdapter!=null){
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}// end class
