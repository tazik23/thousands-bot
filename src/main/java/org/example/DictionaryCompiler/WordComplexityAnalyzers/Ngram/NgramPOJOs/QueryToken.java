package org.example.DictionaryCompiler.WordComplexityAnalyzers.Ngram.NgramPOJOs;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryToken {
    @SerializedName("text")
    private String text;

    @SerializedName("type")
    private String type;

}
