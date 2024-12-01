package org.example.DictionaryCompiler.WordComplexityAnalyzers.Ngram;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.example.DictionaryCompiler.WordComplexityAnalyzers.Ngram.NgramPOJOs.NgramRequest;
import org.example.DictionaryCompiler.WordComplexityAnalyzers.Ngram.NgramPOJOs.NgramResponse;

import java.io.IOException;
import java.util.List;

public class NgramClient {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OkHttpClient client = new OkHttpClient();

    public NgramResponse sendRequest(List<String> words) throws IOException {

        String json = objectMapper.writeValueAsString(new NgramRequest("", words));
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url("https://api.ngrams.dev/eng/batch")
                .post(body)
                .header("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response.code());
            if (response.body() == null) {
                throw new IOException();
            }
            return objectMapper.readValue(response.body().string(), NgramResponse.class);
        }
    }
}
