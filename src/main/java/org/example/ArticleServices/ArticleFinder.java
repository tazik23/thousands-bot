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
    private static final String BASE_URL = "https://www.joelonsoftware.com/";

    @Override
    public List<Article> findArticlesByTheme(String selectedTheme) {
        List<Article> articles = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(BASE_URL).get();
            Elements titles = doc.select("div.editor-category-posts."+ selectedTheme.toLowerCase().replace(" ", "-").replace("software", "story")+ " ul li a");
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

    @Override
    public Article findArticleByTitle(String title) {
        try {
            Document doc = Jsoup.connect(BASE_URL).get();
            Elements titles = doc.select("div.editor-category-posts ul li a");
            for (Element element : titles) {
                String articleTitle = element.text();
                if (articleTitle.equalsIgnoreCase(title) || articleTitle.toLowerCase().contains(title.toLowerCase())) {
                    String articleLink = element.attr("href");
                    return new Article(articleTitle, articleLink);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

