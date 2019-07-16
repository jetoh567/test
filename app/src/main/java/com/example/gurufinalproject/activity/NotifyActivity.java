package com.example.gurufinalproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.gurufinalproject.R;
import com.example.gurufinalproject.fragment.Fragment_1;
import com.example.gurufinalproject.fragment.Fragment_2;
import com.example.gurufinalproject.fragment.Fragment_3;
import com.example.gurufinalproject.fragment.Fragment_4;
import com.example.gurufinalproject.fragment.Fragment_5;
import com.google.android.material.tabs.TabLayout;

// 메인 신고글 페이지 (신고내용 설명 - fragment*5)
public class NotifyActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);

        // 탭 설정
        mTabLayout.addTab(mTabLayout.newTab().setText("외부침입"));
        mTabLayout.addTab(mTabLayout.newTab().setText("공공기물"));
        mTabLayout.addTab(mTabLayout.newTab().setText("야생동물"));
        mTabLayout.addTab(mTabLayout.newTab().setText("분실물"));
        mTabLayout.addTab(mTabLayout.newTab().setText("기타"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // viewPager 생성
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        // 탭과 adapter 연결
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }// en Oncreate

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private int tabSize;

        public ViewPagerAdapter(FragmentManager fm, int count) {
            super(fm);
            this.tabSize = count;

        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Fragment_1();
                case 1:
                    return new Fragment_2();
                case 2:
                    return new Fragment_3();
                case 3:
                    return new Fragment_4();
                case 4:
                    return new Fragment_5();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabSize;
        }
    }
}//end class
