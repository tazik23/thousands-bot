package org.example.DictionaryCompiler.WordComplexityAnalyzers.Ngram.NgramPOJOs;

import java.util.List;

public class NgramResponse {
    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
