package org.example.Translator;

import org.example.Translator.TranslationPOJOs.TranslationResponse;
import org.example.Utils.io.DocxReader;
import org.example.Utils.io.DocxWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Translator implements ITranslator {
    private final YandexTranslateClient client = new YandexTranslateClient();
    private final String targetLanguage;
    private final int maxLength = 10000;

    public Translator(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    @Override
    public String translate(String text) throws IOException {
        return translate(List.of(text)).get(0);
    }

    @Override
    public List<String> translate(List<String> text) throws IOException {
        List<List<String>> splitText = splitByMaxLength(text);
        List<String> translations = new ArrayList<>();

        for (var part : splitText) {
            TranslationResponse response = client.getTranslate(part, targetLanguage);
            if (response.getTranslations() != null && !response.getTranslations().isEmpty()) {
                for (var translation : response.getTranslations()) {
                    translations.add(translation.getText());
                }
            }
            else throw new IOException("translation response is null or empty");
        }
        return translations;
    }

    @Override
    public File translateFile(File file) {
        File translatedFile = new File("Translated" + file.getName());

        try (DocxReader reader = new DocxReader(file);
             DocxWriter writer = new DocxWriter(translatedFile)) {
            List<String> paragraphs = reader.read();
            List<String> translatedParagraphs = translate(paragraphs);
            writer.write(translatedParagraphs);
            writer.save();

            return translatedFile;
        }
        catch (Exception e) {
            return null;
        }
    }
    private List<List<String>> splitByMaxLength(List<String> text) {
        List<List<String>> texts = new ArrayList<>();
        List<String> currentList = new ArrayList<>();
        int currentLength = 0;

        for (String str : text) {
            int strLength = str.length();
            if (currentLength + strLength > maxLength) {
                texts.add(currentList);
                currentList = new ArrayList<>();
                currentLength = 0;
            }
            currentList.add(str);
            currentLength += strLength;
        }
        if (!currentList.isEmpty()) {
            texts.add(currentList);
        }
        return texts;
    }
}
