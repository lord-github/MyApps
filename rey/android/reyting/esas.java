package com.bbstudios.reyting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class esas extends AppCompatActivity {
TabLayout tabLayout;
ViewPager2 viewPager2;
Intent intent
        ;
ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esas);
tabLayout=findViewById(R.id.tabLayout);
intent=getIntent();
viewPager2=findViewById(R.id.Viewpager);
viewPagerAdapter = new ViewPagerAdapter(this,intent.getStringExtra("fc"),
        intent.getStringExtra("ip"));
viewPager2.setAdapter(viewPagerAdapter);
tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager2.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
});

viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
        tabLayout.getTabAt(position).select();
    }
});
    }
}