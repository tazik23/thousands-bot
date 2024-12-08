package org.example.ArticleServices;

import org.example.Models.Article;

import java.util.List;

public interface IArticleFinder {
    List<Article> findArticlesByTheme(String selectedTheme);
    Article findArticleByTitle(String title);
}
