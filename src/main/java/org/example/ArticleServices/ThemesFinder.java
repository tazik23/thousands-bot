package org.example.ArticleServices;

import org.example.Utils.Consts;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThemesFinder implements IThemesFinder {

    @Override
    public List<String> getThemeNames() {
        List<String> themeNames = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(Consts.BASE_URL).get();
            Elements themeElements = doc.select("div.editor-reading-lists > h3.editor-reading-list-title > a");

            for (Element themeElement : themeElements) {
                themeNames.add(themeElement.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return themeNames;
    }
}

