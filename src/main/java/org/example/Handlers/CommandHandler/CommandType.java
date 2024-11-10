package org.example.Handlers.CommandHandler;

public enum CommandType {
    START ("/start"),
    THOUSANDS ("/thousands");

    private final String description;

    CommandType(String description)
    {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
