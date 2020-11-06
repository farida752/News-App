package com.example.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.model.Article;
import com.example.newsapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ArticleViewHolder> {

    public interface onItemClickListener{
        void onArrowClick(boolean isExpandable,int position);
        void onSeeMoreClick(String articleUrl);
        void onArticleImageClick(String imageUrl);
    }

    private ArrayList<Article> data;
    onItemClickListener listener;
    //Context context;
    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public RecyclerAdapter(ArrayList<Article> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_layout,parent,false);
        ArticleViewHolder holder= new ArticleViewHolder(v);
       // this.context=parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
    Article currentArticle= data.get(position);
    holder.textViewTitle.setText(currentArticle.getTitle());
    holder.textViewSectionName.setText(currentArticle.getSectionName());
    holder.textViewPublishDate.setText(currentArticle.getPublishDate());
    holder.textViewAuthor.setText(currentArticle.getAuthor());

    Context context = holder.imageViewArticle.getContext();
    Picasso.with(context).load(currentArticle.getArticleImage()).fit().centerCrop().into(holder.imageViewArticle);

    if(currentArticle.isExpandable()){
        holder.layoutExpandable.setVisibility(View.VISIBLE);
    }else{
        holder.layoutExpandable.setVisibility(View.GONE);
    }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitleText;
        TextView textViewTitle;
        TextView textViewSectionNameText;
        TextView textViewSectionName;
        TextView textViewPublishDateText;
        TextView textViewPublishDate;
        TextView textViewAuthorText;
        TextView textViewAuthor;
        ImageView imageViewArticle;
        ImageView imageViewArrow;
        Button buttonSeeMore;
        LinearLayout layoutExpandable;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitleText= itemView.findViewById(R.id.text_view_title_text);
            textViewTitle  = itemView.findViewById(R.id.text_view_title);

            textViewSectionNameText=itemView. findViewById(R.id.text_view_section_text);
            textViewSectionName= itemView.findViewById(R.id.text_view_section);

            textViewPublishDateText=itemView. findViewById(R.id.text_view_publish_date_text);
            textViewPublishDate=itemView. findViewById(R.id.text_view_publish_date);

            textViewAuthorText= itemView.findViewById(R.id.text_view_author_name_text);
            textViewAuthor= itemView.findViewById(R.id.text_view_author_name);

            imageViewArticle= itemView.findViewById(R.id.image_article);
            imageViewArrow= itemView.findViewById(R.id.image_arrow);
            buttonSeeMore= itemView.findViewById(R.id.button_see_more);
            layoutExpandable= itemView.findViewById(R.id.layout_expandaple);

            buttonSeeMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            Article currentArticle=data.get(position);
                            listener.onSeeMoreClick(currentArticle.getWebUrl());
                        }
                    }
                }
            });

            imageViewArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            Article currentArticle = data.get(position);
                            listener.onArrowClick(currentArticle.isExpandable(),position);
                        }
                    }
                }
            });

            imageViewArticle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null) {
                      int position = getAdapterPosition();
                      if (position != RecyclerView.NO_POSITION) {
                        Article currentArticle = data.get(position);
                        listener.onArticleImageClick(currentArticle.getArticleImage());
                    }
                }
                }
            });

        }
    }
}
