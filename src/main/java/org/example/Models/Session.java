package org.example.Models;

import java.io.File;
import java.util.List;


public class Session {

    private final long id;
    private File selectedArticle;
    private File translatedArticle;
    private File dictionary;
    private List<Article> suggestedArticles;
    private String  selectedTheme;
    private List<String> suggestedThemes;

    public Session(long id) { this.id = id; }

    public long getId() { return id; }

    public List<Article> getSuggestedArticles(){
        return suggestedArticles;
    }

    public List<String> getSuggestedThemes(){
        return suggestedThemes;
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

    public String getSelectedTheme() {return selectedTheme;}

    public void setSelectedArticle(File file){
        selectedArticle = file;
    }

    public void setSelectedTheme(String theme){ selectedTheme = theme;}

    public void setTranslatedArticle(File translatedArticle) {
        this.translatedArticle = translatedArticle;
    }

    public void setDictionary(File dictionary) {
        this.dictionary = dictionary;
    }

    public void setSuggestedArticles(List<Article> suggestedArticles) {
        this.suggestedArticles = suggestedArticles;
    }

    public void setSuggestedThemes(List<String> suggestedThemes){
        this.suggestedThemes = suggestedThemes;
    }
}

