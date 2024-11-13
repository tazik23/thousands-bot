package org.example.Translator.TranslationPOJOs;


import java.util.List;

public class TranslationRequest {

    private final String folderId;
    private final List<String> texts;
    private final String targetLanguageCode;

    public TranslationRequest(String folderId,
                              List<String> texts,
                              String targetLanguageCode) {
        this.folderId = folderId;
        this.texts = texts;
        this.targetLanguageCode = targetLanguageCode;
    }

    public String getFolderId() {
        return folderId;
    }

    public List<String> getTexts() {
        return texts;
    }

    public String getTargetLanguageCode() {
        return targetLanguageCode;
    }
}
