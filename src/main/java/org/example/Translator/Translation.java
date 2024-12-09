package org.example.Translator;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Translation {
    @SerializedName("text")
    private String text;

    @SerializedName("detectedLanguageCode")
    private String detectedLanguageCode;
}

