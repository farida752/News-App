package com.example.newsapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.newsapp.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageFragment extends Fragment {



public static final String BUNDLE_TAG="com.example.newsapp.fragment.BUNDLE_TAG";
private String imageUrl;
ImageView onClickImage;

    public ImageFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static ImageFragment newInstance(String imageUrl) {
        Bundle bundle=new Bundle();
        bundle.putString(BUNDLE_TAG,imageUrl);
        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        imageUrl=bundle.getString(BUNDLE_TAG);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         onClickImage=view.findViewById(R.id.on_click_image);
        Picasso.with(onClickImage.getContext()).load(imageUrl).fit().centerCrop().into(onClickImage);
    }


}