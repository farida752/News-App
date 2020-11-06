package com.example.newsapp.model;

public class Article {
    String articleImage;
    String title;
    String sectionName;
    String publishDate;
    String author;
    String webUrl;
    boolean isExpandable;

    public Article(String articleImage, String title, String sectionName, String publishDate, String author, String webUrl) {
        this.articleImage = articleImage;
        this.title = title;
        this.sectionName = sectionName;
        this.publishDate = publishDate;
        this.author = author;
        this.webUrl=webUrl;
        this.isExpandable=false;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public String getTitle() {
        return title;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleImage='" + articleImage + '\'' +
                ", title='" + title + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", author='" + author + '\'' +
                ", webUrl='" + webUrl + '\'' +
                '}';
    }
}
