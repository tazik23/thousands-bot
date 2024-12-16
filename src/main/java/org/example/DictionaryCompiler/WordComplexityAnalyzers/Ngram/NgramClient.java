package org.example.DictionaryCompiler.WordComplexityAnalyzers.Ngram;

import com.fasterxml.jackson.core.JsonParseException;
import okhttp3.*;
import org.apache.http.HttpException;
import org.example.DictionaryCompiler.WordComplexityAnalyzers.Ngram.NgramPOJOs.NgramRequest;
import org.example.DictionaryCompiler.WordComplexityAnalyzers.Ngram.NgramPOJOs.NgramResponse;
import org.example.DictionaryCompiler.WordComplexityAnalyzers.Ngram.NgramPOJOs.Result;
import org.example.Utils.BaseHttpClient;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NgramClient extends BaseHttpClient {
    private static final String url = "https://api.ngrams.dev/eng/batch";
    private static final String flag = "";

    public Map<String, Long> sendRequest(List<String> words) {
        try {
            Request request = createRequest(words);
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new HttpException("Request error: " + response.code());
                }
                if (response.body() == null) {
                    throw new IOException("Response body is null");
                }
                String jsonResponse = response.body().string();
                NgramResponse ngramResponse =
                        gson.fromJson(jsonResponse, NgramResponse.class);

                List<Result> ngrams = ngramResponse.getResults();
                return ngrams.stream()
                        .collect(Collectors.toMap(
                                n -> n.getQueryTokens().get(0).getText(),
                                n -> n.getNgrams().get(0).getAbsTotalMatchCount()
                        ));
            }
        } catch (Exception e) {
            return null;
        }
    }
    private Request createRequest(List<String> words) throws JsonParseException {
        NgramRequest ngramRequest = new NgramRequest(flag, words);
        String jsonRequest = gson.toJson(ngramRequest);
        if(jsonRequest == null || jsonRequest.isEmpty()) {
            throw new JsonParseException("Failed to serialize object");
        }
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        return new Request.Builder()
                .url(url)
                .post(RequestBody.create(jsonRequest, JSON))
                .build();
    }
}

