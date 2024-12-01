package org.example.DictionaryCompiler.WordComplexityAnalyzers.Ngram.NgramPOJOs;

import java.util.List;

public class Ngram {
    private String id;
    private long absTotalMatchCount;
    private double relTotalMatchCount;
    private List<Token> tokens;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getAbsTotalMatchCount() {
        return absTotalMatchCount;
    }

    public void setAbsTotalMatchCount(long absTotalMatchCount) {
        this.absTotalMatchCount = absTotalMatchCount;
    }

    public double getRelTotalMatchCount() {
        return relTotalMatchCount;
    }

    public void setRelTotalMatchCount(double relTotalMatchCount) {
        this.relTotalMatchCount = relTotalMatchCount;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }
}
