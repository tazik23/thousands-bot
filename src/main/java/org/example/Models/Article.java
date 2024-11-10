package org.example.Models;

public class Article {
    private String title;
    private String link;

    public Article(String title, String link){
        this.title = title;
        this.link = link;
    }

    public String getLink()
    {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
