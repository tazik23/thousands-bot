package org.example.Translator;

import org.example.Translator.TranslationPOJOs.TranslationResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Translator implements ITranslator {
    private final YandexTranslateClient client = new YandexTranslateClient();

    @Override
    public String translate(String text) throws IOException {
        TranslationResponse response = client.getTranslate(List.of(text));
        if (response.getTranslations() != null && !response.getTranslations().isEmpty()) {
            return response.getTranslations().get(0).getText();
        }
        else throw new IOException();
    }

    @Override
    public File translateFile(File file) throws IOException {
        return null;
    }
}
