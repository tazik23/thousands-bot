package org.example.ArticleServices;

import org.example.Models.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.example.Utils.Consts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ArticleFinder implements IArticleFinder {
    private final ThemesFinder themesFinder;

    public ArticleFinder(ThemesFinder themesFinder) {
        this.themesFinder = themesFinder;
    }

    @Override
    public List<Article> findArticlesByTheme(String selectedTheme) {
        List<Article> articles = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(Consts.BASE_URL).get();
            Elements titles = doc.select("div.editor-category-posts."+ selectedTheme.toLowerCase().replace(" ", "-")+ " ul li a");
            for (Element title : titles) {
                String articleTitle = title.text();
                String articleLink = title.attr("href");
                articles.add(new Article(articleTitle, articleLink));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articles;
    }

}

