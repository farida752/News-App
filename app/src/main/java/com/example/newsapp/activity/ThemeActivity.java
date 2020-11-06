package com.example.newsapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.newsapp.R;

public class ThemeActivity extends AppCompatActivity {
public static final String THEME_TAG = "com.example.newsapp.activity.THEME_TAG";
    SharedPreferences sharedPreferences;
    String theme;
    boolean isSaved=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
         sharedPreferences=getSharedPreferences("themes", Context.MODE_PRIVATE);

        RadioGroup radioGroup=findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id= radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton=findViewById(id);
                 theme =radioButton.getText().toString();


            }
        });

        final ImageView imageViewCheck = findViewById(R.id.image_check);
        final TextView textViewSave = findViewById(R.id.text_view_save);
        imageViewCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if(isSaved==false) {
                    imageViewCheck.setImageResource(R.drawable.ic_green_check);
                    textViewSave.setText("Saved");
                    textViewSave.setTextColor(Color.GREEN);

                    editor.putString(THEME_TAG, theme);
                    editor.commit();
                }else{
                    imageViewCheck.setImageResource(R.drawable.ic_gray_check);
                    textViewSave.setText("Save");
                    textViewSave.setTextColor(Color.GRAY);

                    editor.clear();
                    editor.commit();
                }
            }
        });

    }
}