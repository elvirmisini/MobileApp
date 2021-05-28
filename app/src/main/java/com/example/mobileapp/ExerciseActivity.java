package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        TabLayout tabLayout = findViewById(R.id.tabBar);
        TabItem Success = findViewById(R.id.Success);
        TabItem Food = findViewById(R.id.Food);
        TabItem Mode = findViewById(R.id.Mode);
        TabItem Time = findViewById(R.id.Time);

        ViewPager viewPager = findViewById(R.id.viewPager);

        PagerAdapter pagerAdapter = new
                PagerAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



}