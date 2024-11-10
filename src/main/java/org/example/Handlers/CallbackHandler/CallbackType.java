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

    public static CallbackType fromDescription(String description) {
        for (CallbackType value : CallbackType.values()) {
            if (value.getDescription().equalsIgnoreCase(description)) {
                return value;
            }
        }
        throw new IllegalArgumentException("No enum constant with description " + description);
    }
}
