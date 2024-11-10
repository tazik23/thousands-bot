package org.example.Translator;

import com.fasterxml.jackson.databind.ObjectMapper;

public class YandexTranslateClient {
    private final String url = "https://translate.api.cloud.yandex.net/translate/v2/translate";
    private final String ApiKey = System.getenv("API_KEY");
    private final String folderId = System.getenv("FOLDER_ID");
    private final String targetLanguage = "ru";

    private final ObjectMapper objectMapper = new ObjectMapper();


}
