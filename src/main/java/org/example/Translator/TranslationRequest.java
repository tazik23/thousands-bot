package org.example.Translator;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class TranslationRequest {
    @SerializedName("folderId")
    private  String folderId;

    @SerializedName("texts")
    private  List<String> texts;

    @SerializedName("targetLanguageCode")
    private  String targetLanguageCode;
}

