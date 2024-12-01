package org.example.DictionaryCompiler.WordComplexityAnalyzers;

import org.example.DictionaryCompiler.WordComplexityAnalyzers.Ngram.NgramClient;
import org.example.DictionaryCompiler.WordComplexityAnalyzers.Ngram.NgramPOJOs.Result;

import java.util.List;

public class WordFrequencyAnalyzer {
    private final NgramClient client = new NgramClient();
    private final double coefficient = 7E8;
    private final double limitOfRange = 10.;

    public double analyze(String word) {
        try {
            List<Result> results = client.sendRequest(List.of(word)).getResults();
            long totalMatchCount = results.get(0).getNgrams().get(0).getAbsTotalMatchCount();
            return normalize(totalMatchCount);
        }
        catch (Exception e){
            return -1;
        }
    }
    public double normalize(long totalMatchCount){
        return limitOfRange - totalMatchCount / coefficient;
    }
}
