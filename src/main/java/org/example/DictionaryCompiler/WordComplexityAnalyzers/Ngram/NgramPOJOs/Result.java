package org.example.DictionaryCompiler.WordComplexityAnalyzers.Ngram.NgramPOJOs;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Result {
    @SerializedName("queryTokens")
    private List<QueryToken> queryTokens;

    @SerializedName("ngrams")
    private List<Ngram> ngrams;

}
