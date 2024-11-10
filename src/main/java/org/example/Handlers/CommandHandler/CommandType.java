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

    public static CommandType fromDescription(String description) {
        for (CommandType value : CommandType.values()) {
            if (value.getDescription().equalsIgnoreCase(description)) {
                return value;
            }
        }
        throw new IllegalArgumentException("No enum constant with description " + description);
    }

}
