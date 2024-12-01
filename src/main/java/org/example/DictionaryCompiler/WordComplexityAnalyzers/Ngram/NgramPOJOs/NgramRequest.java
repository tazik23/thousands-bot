package org.example.DictionaryCompiler.WordComplexityAnalyzers.Ngram.NgramPOJOs;

import java.util.List;

public class NgramRequest {
    private String flags;
    private List<String> queries;

    public NgramRequest(String flags, List<String> queries) {
        this.flags = flags;
        this.queries = queries;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public List<String> getQueries() {
        return queries;
    }

    public void setQueries(List<String> queries) {
        this.queries = queries;
    }
}
