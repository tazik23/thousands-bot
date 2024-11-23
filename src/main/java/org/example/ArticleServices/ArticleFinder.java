package org.example.ArticleServices;

import org.example.Models.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArticleFinder implements IArticleFinder {
    private static final String url = "https://www.joelonsoftware.com/";

    @Override
    public List<Article> findArticles() {
        List<Article> articles = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements titles = doc.select("div.editor-category-posts.top-10 ul li a");
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

