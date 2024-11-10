package org.example.Handlers.CommandHandler;

public enum CommandType {
    START ("/start"),
    TYSIACHI ("/tysiachi");

    private final String description;

    CommandType(String description)
    {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
