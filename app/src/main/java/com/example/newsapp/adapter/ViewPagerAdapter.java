package com.example.newsapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.newsapp.fragment.CustomSectionFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[]tabtitle={"Environment","Art","Business","Education","Fashion","Science","Politics","Sports","Technology","Travel","KPop"};
    private int count=tabtitle.length;
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       return CustomSectionFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitle[position];
    }
}
