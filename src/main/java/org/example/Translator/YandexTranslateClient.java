package org.example.Translator;

import com.fasterxml.jackson.core.JsonParseException;
import okhttp3.*;
import org.apache.http.HttpException;
import org.example.Utils.BaseHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class YandexTranslateClient extends BaseHttpClient {
    private static final String url = "https://translate.api.cloud.yandex.net/translate/v2/translate";
    private static final  String ApiKey = System.getenv("API_KEY");
    private static final String folderId = System.getenv("FOLDER_ID");
    private static final String targetLanguage = "ru";

    private static final Logger logger = LoggerFactory.getLogger(YandexTranslateClient.class);

    List<String> sendRequest(List<String> texts) {
        try {
            Request request = createRequest(texts);
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new HttpException("Request error: " + response.code());
                }
                if (response.body() == null) {
                    throw new IOException("Response body is null");
                }
                String jsonResponse = response.body().string();
                TranslationResponse translationResponse =
                        gson.fromJson(jsonResponse, TranslationResponse.class);
                List<Translation> translations = translationResponse.getTranslations();

                return translations.stream()
                        .map(Translation::getText)
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
            return null;
        }
    }

    private Request createRequest(List<String> texts) throws JsonParseException {
        TranslationRequest translationRequest = new TranslationRequest(folderId, texts, targetLanguage);
        String jsonRequest = gson.toJson(translationRequest);
        if(jsonRequest == null || jsonRequest.isEmpty()) {
            throw new JsonParseException("Failed to serialize object");
        }
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        return new Request.Builder()
                .url(url)
                .header("Authorization", ("Api-Key " + ApiKey))
                .post(RequestBody.create(jsonRequest, JSON))
                .build();
    }
}
