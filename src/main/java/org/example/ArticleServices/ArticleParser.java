package org.example.ArticleServices;

import org.example.Models.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArticleParser implements IArticleParser {

    @Override
    public File parse(Article article) throws IOException {

        File file = new File(article.getTitle() + ".txt");

        // получаем текст статьи
        List<String> articleTexts = getText(article);


        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Title: " + article.getTitle() + "\n");
            writer.write("Link: " + article.getLink() + "\n");
            writer.write("Content:\n");
            for (String paragraph : articleTexts) {
                writer.write(paragraph + "\n");
            }
        }
        return file;
    }

    private List<String> getText(Article article) {
        List<String> articleTexts = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(article.getLink()).get();
            Elements paragraphs = doc.select(".entry-content p");
            for (Element paragraph : paragraphs) {
                articleTexts.add(paragraph.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articleTexts;
    }

}
