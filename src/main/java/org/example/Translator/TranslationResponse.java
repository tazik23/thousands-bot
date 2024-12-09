package org.example.Translator;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class TranslationResponse {
    @SerializedName("translations")
    private List<Translation> translations;
}
