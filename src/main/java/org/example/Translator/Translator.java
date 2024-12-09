package org.example.Translator;


import lombok.AllArgsConstructor;
import org.example.Utils.io.DocxReader;
import org.example.Utils.io.DocxWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Translator implements ITranslator {
    private final YandexTranslateClient client;
    private final static int CHARACTER_LIMIT = 10000;

    @Override
    public String translate(String text) throws IOException {
        return translate(List.of(text)).get(0);
    }

    @Override
    public List<String> translate(List<String> text) throws IOException {
        if(text == null || text.isEmpty()) {
            throw new IOException();
        }

        List<List<String>> splitText = splitByCharacterLimit(text);
        List<String> translations = splitText.stream()
                .map(client::sendRequest)
                .filter(t -> t != null && !t.isEmpty())
                .flatMap(List::stream)
                .collect(Collectors.toList());

        if(translations.isEmpty()) {
            throw new IOException();
        }

        return translations;
    }

    @Override
    public File translateFile(File file) throws Exception {
        if(file == null) {
            throw new IOException("file is null");
        }

        File translatedFile = new File("Translated_" + file.getName());

        try (DocxReader reader = new DocxReader(file);
             DocxWriter writer = new DocxWriter(translatedFile)) {
            List<String> paragraphs = reader.read();
            List<String> translatedParagraphs = translate(paragraphs);
            writer.write(translatedParagraphs);
            writer.save();

            return translatedFile;
        }
    }
    private List<List<String>> splitByCharacterLimit(List<String> text) {
        List<List<String>> result = new ArrayList<>();
        List<String> currentList = new ArrayList<>();
        int currentLength = 0;

        for (String str : text) {
            int strLength = str.length();
            if(currentLength + strLength > CHARACTER_LIMIT) {
                result.add(currentList);
                currentList.clear();
                currentLength = 0;
            }
            else {
                currentList.add(str);
                currentLength += strLength;
            }
        }
        if (!currentList.isEmpty()) {
            result.add(currentList);
        }
        return result;
    }
}
