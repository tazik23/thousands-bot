package org.example.Handlers.CallbackHandler;

public enum CallbackType {
    ARTICLE("article"),
    TRANSLATION ("translation"),
    DICTIONARY("dictionary");

    private final String description;
    private CallbackType(String description)
    {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

}
