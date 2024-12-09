package org.example.DictionaryCompiler.WordComplexityAnalyzers.Ngram.NgramPOJOs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter

public class Ngram {
    @SerializedName("id")
    private String id;

    @SerializedName("absTotalMatchCount")
    private long absTotalMatchCount;

    @SerializedName("relTotalMatchCount")
    private double relTotalMatchCount;

    @SerializedName("tokens")
    private List<Token> tokens;

}
