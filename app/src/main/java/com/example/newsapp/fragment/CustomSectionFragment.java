package com.example.newsapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.newsapp.R;
import com.example.newsapp.activity.MainActivity;
import com.example.newsapp.activity.WebViewActivity;
import com.example.newsapp.adapter.RecyclerAdapter;
import com.example.newsapp.model.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomSectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomSectionFragment extends Fragment {
     RecyclerView articleRecyclerView;
     RecyclerAdapter adapter;
    public static final String WEB_VIEW_INTENT="com.example.newsapp.Activity.WEB_VIEW_INTENT";
    public static final String POSITION_TAG = "com.example.newsapp.fragment.POSITION_TAG ";
    public static final String SEARCH_URL_TAG = "com.example.newsapp.fragment.SEARCH_URL_TAG";
    int position ;
    String searchUrl;




   /* public CustomSectionFragment(int position) {
        this.position=position;
    }

    public CustomSectionFragment(String searchUrl) {
       this.searchUrl=searchUrl;
       this.position=-1;
    }*/
    public CustomSectionFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        position=bundle.getInt(POSITION_TAG);
        searchUrl=bundle.getString(SEARCH_URL_TAG);
    }

    // TODO: Rename and change types and number of parameters
    public static CustomSectionFragment newInstance(int position) {
        Bundle bundle=new Bundle();
        bundle.putInt(POSITION_TAG,position);
        bundle.putString(SEARCH_URL_TAG,"");
        CustomSectionFragment fragment = new CustomSectionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
   public static CustomSectionFragment newInstance(String searchUrl) {
        Bundle bundle=new Bundle();
        bundle.putString(SEARCH_URL_TAG,searchUrl);
        bundle.putInt(POSITION_TAG,-1);
        CustomSectionFragment fragment = new CustomSectionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v=inflater.inflate(R.layout.fragment_custom_section, container, false);
        articleRecyclerView = v.findViewById(R.id.article_recycler_view);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String jsonweburl="";
        switch (position){
            case -1:
                jsonweburl = searchUrl;
                break;
            case 0:
                jsonweburl = "https://content.guardianapis.com/search?format=json&section=environment" +
                        "&query-fields=environment&api-key=03f592ad-54d1-4fd8-bc23-329de23e3216&show-fields=all&%20page-size=50";
                break;
            case 1:
                jsonweburl = "https://content.guardianapis.com/search?format=json&section=culture" +
                        "&query-fields=culture&api-key=03f592ad-54d1-4fd8-bc23-329de23e3216&show-fields=all&%20page-size=50";
                break;
            case 2:
                jsonweburl = "https://content.guardianapis.com/search?format=json&section=business" +
                        "&query-fields=business &api-key=03f592ad-54d1-4fd8-bc23-329de23e3216&show-fields=all&%20page-size=50";
                break;
            case 3:
                jsonweburl = "https://content.guardianapis.com/search?format=json&section=education" +
                        "&query-fields=education &api-key=03f592ad-54d1-4fd8-bc23-329de23e3216&show-fields=all&%20page-size=50";
                break;
            case 4:
                jsonweburl = "https://content.guardianapis.com/search?format=json&section=fashion" +
                        "&query-fields=fashion &api-key=03f592ad-54d1-4fd8-bc23-329de23e3216&show-fields=all&%20page-size=50";
                break;
            case 5:
                jsonweburl = "https://content.guardianapis.com/search?format=json&section=science" +
            "&query-fields=science&api-key=03f592ad-54d1-4fd8-bc23-329de23e3216&show-fields=all&%20page-size=50";
                break;
            case 6:
                jsonweburl = "https://content.guardianapis.com/search?format=json&section=politics" +
                        "&query-fields=politics &api-key=03f592ad-54d1-4fd8-bc23-329de23e3216&show-fields=all&%20page-size=50";
                break;
            case 7:
                jsonweburl = "https://content.guardianapis.com/search?format=json&section=sport" +
                        "&query-fields=sport &api-key=03f592ad-54d1-4fd8-bc23-329de23e3216&show-fields=all&%20page-size=50";
                break;
            case 8:
                jsonweburl = "https://content.guardianapis.com/search?format=json&section=technology" +
                        "&query-fields=technology &api-key=03f592ad-54d1-4fd8-bc23-329de23e3216&show-fields=all&%20page-size=50";
                break;
            case 9:
                jsonweburl = "https://content.guardianapis.com/search?format=json&section=travel" +
                        "&query-fields=travel &api-key=03f592ad-54d1-4fd8-bc23-329de23e3216&show-fields=all&%20page-size=50";
                break;
            case 10:
                jsonweburl="https://content.guardianapis.com/search?format=json&q=\"" +"kpop"+
                        "\"&api-key=03f592ad-54d1-4fd8-bc23-329de23e3216&show-fields=all&%20page-size=50";
                break;

            default:
                break;
        }

      getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK ) {
                    FragmentManager fm = getChildFragmentManager();
                    if (fm.getBackStackEntryCount() > 0) {
                    getChildFragmentManager().popBackStack();
                    return true;}
                }
                else {
                getActivity().moveTaskToBack(true);
                return false;}
                return false;
            }
        } );
        URL url=createUrl(jsonweburl);
        new ArticleAsyncTask().execute(url);
    }

    public URL createUrl(String jsonweburl){
        URL url=null;
        try {
            url=new URL(jsonweburl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            //  Toast.makeText(getContext(), "hi from CustomFragment", Toast.LENGTH_SHORT).show();
        }

        return  url;
    }


    public String makeHttpRequest(URL url){
        String jsonRequest="";
        HttpURLConnection httpURLConnection=null;
        InputStream in=null;
        try {
            httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.connect();

            if(httpURLConnection.getResponseCode()==200){
                in=httpURLConnection.getInputStream();
                jsonRequest=readFromStream(in);
            }else{
                Toast.makeText(getContext(), "connection error"+httpURLConnection.getResponseCode(), Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(httpURLConnection!=null){
                httpURLConnection.disconnect();
            }
            if(in!=null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return jsonRequest;
    }

    public String readFromStream(InputStream in){
        BufferedReader reader=new BufferedReader(new InputStreamReader(in, Charset.forName("utf-8")));
        StringBuffer stringBuffer=new StringBuffer();
        String data=null;

        try {
            while((data=reader.readLine())!=null){
                stringBuffer.append(data)  ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

    public ArrayList<Article> extractFeaturesFromJson(String jsonResponse){
        ArrayList<Article> articles=new ArrayList<>();
        try {
            JSONObject root =new JSONObject(jsonResponse);
            JSONObject response=root.getJSONObject("response");
            JSONArray results=response.getJSONArray("results");
            for(int i=0;i<results.length();i++){
                JSONObject currentArticle=results.getJSONObject(i);
                String title= currentArticle.getString("webTitle");
                String sectionName = currentArticle.getString("sectionName");
                String publishDate = currentArticle.getString("webPublicationDate");
                String webUrl = currentArticle.getString("webUrl");
                JSONObject fields=currentArticle.getJSONObject("fields");
                String author =fields.getString("byline");
                String imageArticle=fields.getString("thumbnail");

                articles.add(new Article(imageArticle,title,sectionName,publishDate,author,webUrl));}

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return articles;
    }

    public void updateData(final ArrayList<Article> articles){
        adapter=new RecyclerAdapter(articles);
        articleRecyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        articleRecyclerView.setLayoutManager(layoutManager);

        adapter.setListener(new RecyclerAdapter.onItemClickListener() {
            @Override
            public void onArrowClick(boolean isExpandable, int position) {
                if(isExpandable){
                    articles.get(position).setExpandable(false);
                    adapter.notifyDataSetChanged();
                }else{
                    articles.get(position).setExpandable(true);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onSeeMoreClick(String articleUrl) {
                Intent intent =new Intent(getContext(), WebViewActivity.class);
                intent.putExtra(WEB_VIEW_INTENT,articleUrl);
                startActivity(intent);
            }

            @Override
            public void onArticleImageClick(String imageUrl) {

               getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.place_holder,ImageFragment.newInstance(imageUrl))
                        .addToBackStack(null)
                        .commit();


            }
        });
    }



    class  ArticleAsyncTask extends AsyncTask<URL,Void,String> {


        @Override
        protected String doInBackground(URL... urls) {
            String jsonRequest =makeHttpRequest(urls[0]);
            return jsonRequest;
        }

        @Override
        protected void onPostExecute(String jsonRequest) {
            super.onPostExecute(jsonRequest);
            updateData(extractFeaturesFromJson(jsonRequest));
            //extractFeaturesFromJson(jsonRequest);
        }
    }

/*
public void remove(){
    getChildFragmentManager()
            .beginTransaction()
            .replace(R.id.place_holder,null)
            .commit();
}
*/


}