package org.example.Models;

import java.io.File;
import java.util.Iterator;


public class Session {

    private final long id;
    private File selectedArticle;
    private File translatedArticle;
    private File dictionary;
    private Iterator<Article> suggestedArticles;

    public Session(long id) { this.id = id; }

    public long getId() { return id; }

    public Iterator<Article> getSuggestedArticles(){
        return suggestedArticles;
    }


    public File getSelectedArticle() {
        return selectedArticle;
    }

    public File getTranslatedArticle() {
        return translatedArticle;
    }

    public File getDictionary() {
        return dictionary;
    }

    public void setSelectedArticle(File file){
        selectedArticle = file;
    }

    public void setTranslatedArticle(File translatedArticle) {
        this.translatedArticle = translatedArticle;
    }

    public void setDictionary(File dictionary) {
        this.dictionary = dictionary;
    }

    public void setSuggestedArticles(Iterator<Article> suggestedArticles) {
        this.suggestedArticles = suggestedArticles;
    }

}

