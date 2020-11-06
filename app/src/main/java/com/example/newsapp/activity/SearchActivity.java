package com.example.newsapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.newsapp.R;
import com.example.newsapp.fragment.CustomSectionFragment;

public class SearchActivity extends AppCompatActivity {
    SharedPreferences prefs;
    RelativeLayout searchLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchLayout=findViewById(R.id.search_layout);
        prefs= getSharedPreferences("themes", Context.MODE_PRIVATE);
        setTheme();


        Button buttonSearch=findViewById(R.id.button_search);
        YoYo.with(Techniques.Tada)
                .duration(700)
                .repeat(5)
                .playOn(buttonSearch);

        final EditText editTextSearch=findViewById(R.id.edit_text_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = editTextSearch.getText().toString();

                if(word.trim().equals("")==false){
                String searchUrl = "https://content.guardianapis.com/search?format=json&q=\"" +word+
                "\"&api-key=03f592ad-54d1-4fd8-bc23-329de23e3216&show-fields=all&%20page-size=50";

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_place_holder, /*SearchFragment.newInstance(searchUrl)*/CustomSectionFragment.newInstance(searchUrl))
                        .commit();}
                else {
                    Toast.makeText(SearchActivity.this, "you don't enter any thing", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    public void setTheme(){
        String theme=prefs.getString(ThemeActivity.THEME_TAG,"classic mode");
        if(theme.equalsIgnoreCase("classic mode")){
            searchLayout.setBackground(getResources().getDrawable(R.drawable.shape_classic));
        }else if(theme.equalsIgnoreCase("light mode")){
            searchLayout.setBackground(getResources().getDrawable(R.drawable.shape_light));
        }else if(theme.equalsIgnoreCase("dark mode")){
            searchLayout.setBackground(getResources().getDrawable(R.drawable.shape_dark));
        }else if(theme.equalsIgnoreCase("gloss mode")){
            searchLayout.setBackground(getResources().getDrawable(R.drawable.shape_gloss));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setTheme();
    }
}