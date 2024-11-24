package org.example.Translator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.example.Translator.TranslationPOJOs.TranslationRequest;
import org.example.Translator.TranslationPOJOs.TranslationResponse;

import java.io.IOException;
import java.util.List;

public class YandexTranslateClient {
    private final String ApiKey = System.getenv("API_KEY");
    private final String folderId = System.getenv("FOLDER_ID");

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OkHttpClient client = new OkHttpClient();

    public TranslationResponse getTranslate(List<String> texts, String targetLanguage) throws IOException {
        RequestBody body = formRequestBody(texts, targetLanguage);
        Request request = new Request.Builder()
                .url("https://translate.api.cloud.yandex.net/translate/v2/translate")
                .header("Content-Type", "application/json")
                .header("Authorization", ("Api-Key " + ApiKey))
                .post(body)
                .build();

        return sendRequest(request);
    }

    private TranslationResponse sendRequest(Request request) throws IOException {
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Request error: " + response.code());
            }
            if (response.body() == null){
                throw new IOException("Response body is null");
            }

            return objectMapper
                    .readValue(response.body().string(), TranslationResponse.class);
        }
    }


    private RequestBody formRequestBody(List<String> texts, String targetLanguage) throws JsonProcessingException {
        String json = objectMapper
                .writeValueAsString(new TranslationRequest(folderId, texts, targetLanguage));
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        return RequestBody.create(json, JSON);
    }
}
