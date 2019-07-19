package com.example.gurufinalproject.activity.administrator;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.bean.NoticeBean;

import java.util.List;

public class NoticeAdapter extends BaseAdapter {

    private Context mContext;
    private List<NoticeBean> mNoticeList;


    public NoticeAdapter(Context context, List<NoticeBean> noticeList) {
        mContext = context;
        mNoticeList = noticeList;
    }

    @Override
    public int getCount() {
        return mNoticeList.size();
    }

    @Override
    public Object getItem(int i) {
        return mNoticeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.notice_form,null);

        TextView title, detail, writer, date;

        title = view.findViewById(R.id.NoticeTitle);
        detail = view.findViewById(R.id.NoticeDetail);
        writer = view.findViewById(R.id.NoticeWriter);
        date = view.findViewById(R.id.NoticeDate);

        final NoticeBean noticeBean = mNoticeList.get(i);

        title.setText(noticeBean.noticeTitle);
        detail.setText(noticeBean.noticeDetail);
        writer.setText("작성자 : "+noticeBean.noticeWriter);
        date.setText("작성 날짜 : "+noticeBean.regDate);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext,NoticeDetailActivity.class);
                i.putExtra("notice",noticeBean);
                mContext.startActivity(i);
            }
        });

        return view;
    }
}
