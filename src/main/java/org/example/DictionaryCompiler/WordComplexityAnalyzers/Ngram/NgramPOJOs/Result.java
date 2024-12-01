package org.example.DictionaryCompiler.WordComplexityAnalyzers.Ngram.NgramPOJOs;

import java.util.List;

public class Result {
    private List<QueryToken> queryTokens;
    private List<Ngram> ngrams;

    public List<QueryToken> getQueryTokens() {
        return queryTokens;
    }

    public List<Ngram> getNgrams() {
        return ngrams;
    }
    public void setQueryTokens(List<QueryToken> queryTokens) {
        this.queryTokens = queryTokens;
    }

    public void setNgrams(List<Ngram> ngrams) {
        this.ngrams = ngrams;
    }

}
