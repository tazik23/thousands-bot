package org.example.ArticleServices;

import org.example.Models.Article;
import org.example.Utils.io.DocxWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArticleParser implements IArticleParser {

    @Override
    public File parse(Article article) throws IOException {
        String sanitizedTitle = article.getTitle()
                .replaceAll(" ", "_")
                .replaceAll("[\\\\/:*?\"<>|]", "");
        File file = new File(sanitizedTitle + ".docx");
        DocxWriter writer = new DocxWriter(new FileOutputStream(file));


        List<String> articleTexts = getText(article);


        try {
            writer.writeParagraph("Title: " + article.getTitle() + "\n");
            writer.writeParagraph("Link: " + article.getLink() + "\n");
            writer.writeParagraph("Content:\n");
            for (String paragraph : articleTexts) {
                writer.writeParagraph(paragraph + "\n");
            }
            writer.save();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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
