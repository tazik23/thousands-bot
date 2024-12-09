package org.example.Models;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Iterator;

@Getter
@Setter
public class Session {
    private final long id;
    private File selectedArticle;
    private File translatedArticle;
    private File dictionary;
    private Iterator<Article> suggestedArticles;

    public Session(long id) { this.id = id; }
}

