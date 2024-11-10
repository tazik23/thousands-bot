package org.example.Translator;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.example.Translator.TranslationPOJOs.TranslationRequest;
import org.example.Translator.TranslationPOJOs.TranslationResponse;

import java.io.IOException;
import java.util.List;

public class YandexTranslateClient {
    private final String url = "https://translate.api.cloud.yandex.net/translate/v2/translate";
    private final String ApiKey = System.getenv("API_KEY");
    private final String folderId = System.getenv("FOLDER_ID");
    private final String targetLanguage = "ru";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public TranslationResponse getTranslate(List<String> texts) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String json = objectMapper.writeValueAsString(new TranslationRequest(folderId, texts, targetLanguage));
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .header("Authorization", ("Api-Key " + ApiKey))
                .post(body)
                .build();


        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                return objectMapper.readValue(responseBody, TranslationResponse.class);
            }
        }
        catch (IOException e) {
            //todo: add logging
        }

        return null;
    }
}
