package org.example.DictionaryCompiler.WordComplexityAnalyzers;

import org.example.DictionaryCompiler.WordComplexityAnalyzers.Ngram.NgramClient;
import org.example.DictionaryCompiler.WordComplexityAnalyzers.Ngram.NgramPOJOs.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordFrequencyAnalyzer implements IAnalyzer{
    private final NgramClient client = new NgramClient();
    private static final double coefficient = 7E8;
    private static final double limitOfRange = 10.;

    @Override
    public double analyze(String word) {
        if (word == null || word.isEmpty())
            return -1;

        Map<String, Long> ngrams = client.sendRequest(List.of(word));
        return normalize(ngrams.get(word));
    }

    @Override
    public Map<String, Double> analyze(List<String> words) {
        if (words == null || words.isEmpty()) {
            return new HashMap<>();
        }

        Map<String, Long> ngrams = client.sendRequest(words);
        if (ngrams == null) {
            return new HashMap<>();
        }

        return ngrams.entrySet().stream()
                .filter(entry -> entry != null && entry.getKey() != null
                        && !entry.getKey().isEmpty() && entry.getValue() != null)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> normalize(entry.getValue())
                ));
    }

    public double normalize(long totalMatchCount){
        return limitOfRange - totalMatchCount / coefficient;
    }
}

