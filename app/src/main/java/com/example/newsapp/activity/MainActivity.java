package com.example.newsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.newsapp.adapter.ViewPagerAdapter;
import com.example.newsapp.R;
import com.example.newsapp.fragment.CustomSectionFragment;
import com.example.newsapp.fragment.ImageFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs;
    LinearLayout themeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager=findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout=findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

         themeLayout=findViewById(R.id.theme_layout);
         prefs= getSharedPreferences("themes", Context.MODE_PRIVATE);
        setTheme();

    }


public void setTheme(){
    String theme=prefs.getString(ThemeActivity.THEME_TAG,"classic mode");
    if(theme.equalsIgnoreCase("classic mode")){
        themeLayout.setBackground(getResources().getDrawable(R.drawable.shape_classic));
    }else if(theme.equalsIgnoreCase("light mode")){
        themeLayout.setBackground(getResources().getDrawable(R.drawable.shape_light));
    }else if(theme.equalsIgnoreCase("dark mode")){
        themeLayout.setBackground(getResources().getDrawable(R.drawable.shape_dark));
    }else if(theme.equalsIgnoreCase("gloss mode")){
        themeLayout.setBackground(getResources().getDrawable(R.drawable.shape_gloss));
    }
}

    @Override
    protected void onRestart() {
        super.onRestart();
        setTheme();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        Intent intent;
        switch (id){
            case R.id.item_search:
                 intent=new Intent(this, SearchActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_theme_settings:
                intent=new Intent(this, ThemeActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }
}